/*
pos
cur = 현재 idx
stack에다가 가장 최근에 제거된 pos 저장

*/
import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmds) {
        int cur = k;
        Deque<Integer> del = new ArrayDeque<>();
        boolean[] isDeleted = new boolean[n];
        
        // i의 다음 위치 저장
        int[] next = new int[n];
        // i의 이전 위치 저장
        int[] prev = new int[n];
        for(int i = 0; i<n; i++){
            next[i] = i+1;
            prev[i] = i-1;
        }
        
        for(String cmd : cmds){
            String[] cc = cmd.split(" ");
            // up은 위로 prev써야함
            if(cc[0].equals("U")){
                int num = Integer.parseInt(cc[1]);
                for(int j = 0; j<num; j++){
                    cur = prev[cur];
                }
            }
            else if(cc[0].equals("D")){
                int num = Integer.parseInt(cc[1]);
                for(int j = 0; j<num; j++){
                    cur = next[cur];
                }
            }
            // 삭제 부분에서 매번 다음 위치를 쭉 훑는건 오래걸리는데
            // 점프 위치를 미리 저장하는 배열을 두면?
            else if(cc[0].equals("C")){
                del.addFirst(cur);
                isDeleted[cur] = true;
                
                int pp = prev[cur];
                int nn = next[cur];
                
                // 경계처리
                if(nn != n)
                    prev[nn] = pp;
                if(pp != -1)
                    next[pp] = nn;
                
                if(nn < n)
                    cur = nn;
                else
                    cur = pp;
            }
            // 그러면 여기서도 점프 위치 배열을 수정해야하는데 
            else{
                if(!del.isEmpty()){
                    int dd = del.poll();
                    isDeleted[dd] = false;
                    
                    int pp = prev[dd];
                    int nn = next[dd];

                    if(nn != n)
                        prev[nn] = dd;
                    if(pp != -1)
                        next[pp] = dd;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(boolean b : isDeleted){
            if(!b)
                sb.append("O");
            else
                sb.append("X");
        }
        
        return sb.toString();
    }
}