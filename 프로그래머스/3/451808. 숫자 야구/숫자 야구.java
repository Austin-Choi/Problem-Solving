/*
submit은 int 인수 1개를 받아 String을 리턴하는 함수
submit.apply(i) -> i를 입력받아서 String 리턴값

리턴값 보고 1000~9999 사이의 조건에 가장 가까운 후보 추출해서 guess하기?
n은 submit을 호출할수 있는 최대 횟수
-> dfs같은걸로 현재 사용 가능한 boolean[10] 이랑 strike, ball 가지고 갈까
n은 max depth로 쓰면 되고 

시작은 비밀번호에 숫자가 서로다른 숫자이므로 1234로 시작 같으면 굳이 넣을 필요도 없음.
-> 근데 ball, strike만 알려주니까 이걸로는 안될거같음
-> 최소 n이 6 제한이 있는데 음..
-> DFS는 처음 후보 쓸때만 하기

9*8*7*6 = 3024가지 가능
0) dfs로 3024가지 비밀번호 다 만들어놓기
1) Set에서 각 후보를 실제 답이라고 가정하고 지금 넣어본거를 strike, ball 비교해서 결과 같은거만 남김
2) minimax로 가장 적합한 다음 후보 골라서 best 업데이트
1~2 strike 4나올때까지 반복
*/
import java.util.*;
import java.util.function.Function;

class Solution {
    // 0:strike, 1: ball
    int[] stoi(String s){
        char[] temp = s.toCharArray();
        int strike = temp[0]-'0';
        int ball = temp[3]-'0';
        return new int[]{strike, ball};
    }
    
    // 3024가지 후보 숫자 처음에 만들기
    int[] pool = {1,2,3,4,5,6,7,8,9};
    //boolean[] candidates = new boolean[10000];
    Set<Integer> cand = new HashSet<>();
    ArrayList<Integer> allNumbers = new ArrayList<>();
    boolean[] visited = new boolean[10];
    void dfs(int depth, int[] cur){
        if(depth == 4){
            int num = cur[0]*1000+cur[1]*100+cur[2]*10+cur[3];
            cand.add(num);
            allNumbers.add(num);
            return;
        }
        
        for(int i = 0; i<9; i++){
            int num = pool[i];
            if(visited[num])
                continue;
            visited[num] = true;
            cur[depth] = num;
            dfs(depth+1, cur);
            visited[num] = false;
        }
    }
    
    // 0: strike, 1: ball
    // A가 실제 비밀번호라고 가정하고 B랑 비교했을때 B의 결과
    int[] compare(int A, int B){
        int[] aa = new int[]{A/1000, ((A%1000)/100), ((A%100/10)), A%10};
        int[] bb = new int[]{B/1000, ((B%1000)/100), ((B%100/10)), B%10};
        
        int strike = 0;
        int ball = 0;
        boolean[] isB = new boolean[10];
        
        //ball 계산할때 따로 isB 먼저 해줘야함!!
        for(int i = 0; i<4; i++){
            isB[bb[i]] = true;
        }
        
        for(int i = 0; i<4; i++){
            if(aa[i] == bb[i])
                strike++;
            else{
                if(isB[aa[i]])
                    ball++;
            }
        }
        
        return new int[]{strike, ball};
    }
    
    public int solution(int N, Function<Integer, String> submit) {
        // 후보군 초기 set 설정
        dfs(0, new int[4]);
        
        int best = cand.iterator().next();
        for(int n = 0; n<N; n++){
            String rst = submit.apply(best);
            int[] guess = stoi(rst);
            
            // 4 strike이면 답
            if(guess[0] == 4)
                return best;
            
            // 먼저 guess의 strike, ball에 해당하지 않는것들을 cand에서 제거함
            // -> 각 curCand를 실제 비밀번호라 생각하고 rst의 strike, ball과 비교하기
            Iterator<Integer> it = cand.iterator();
            while(it.hasNext()){
                int curCand = it.next();
                int[] curRst = compare(curCand, best);
                if(curRst[0] != guess[0] || curRst[1] != guess[1])
                    it.remove();
            }
            
            // 후보군에 하나 남아도 그게 답
            // 후보군을 줄인 직후에 잘라줘야 나중에 submit 호출 횟수 줄일 수 있음.
            if(cand.size() == 1)
                return cand.iterator().next();
            
            // minimax로 최적후보 찾아내기
            // 줄인 후보군만 쓰면 질문에서 얻을 정보량이 줄어서 전체 3024개 후보군을 가져다가 써야함
            int minWorst = Integer.MAX_VALUE;
            int nextBest = -1;
            for(int curGuess : allNumbers){
                // strike, ball은 0~4로 나오므로 
                int[][] count = new int[5][5];
                
                for(int curCand : cand){
                    int[] result = compare(curCand, curGuess);
                    count[result[0]][result[1]]++;
                }
                
                // 이걸 제출했을때 가장 많이 남는 후보 수
                int worst = 0;
                for(int s = 0; s<=4; s++){
                    for(int b = 0; b<=4; b++){
                        worst = Math.max(worst, count[s][b]);
                    }
                }
                
                // 최악의 경우에도 가장 적게 남는 것이 다음 best
                if(worst < minWorst){
                    minWorst = worst;
                    nextBest = curGuess;
                }
            }
            best = nextBest;
        }
        return 0;
    }
}