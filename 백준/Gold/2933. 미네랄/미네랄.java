/*
1) 배열안쓰고 bool isLeft 바꿔주기 (shooting)
2)board 맨 아랫줄에서(R-1) x면 모두 시작 좌표로 넣어서 bfs돌려서 땅에 붙어있는 x구별함 (bool touchdown[][])
3)board에서 x인데 touchdown이 false인건 떠있는 클러스터임 (floats에 저장)
-> floats 크기 0이면 아래 스킵함 (떨어질거 없음)
4)모든 floats 좌표(ci,cj)에 대해서 ni=ci+1이라하고
아래 방향으로 ni,cj 가 board에서 .이고 ni < R인동안 ni++하고
ni -= (ci+1)하고 mindrop에 ni로 갱신함
5)floats 좌표는 board에 .으로 일단 바꾸고 floats ci+mindrop, cj 값의 보드에 x채움
 */
import java.util.*;
import java.io.*;
public class Main {
    //왼오위아래
    static int[] di = {0,0,-1,1};
    static int[] dj = {-1,1,0,0};
    static int R;
    static int C;
    static char[][] board;
    static int N;
    //1-based로 입력들어옴
    static void shooting(int height, boolean isLeft){
        int i = R-height;
        if(!isLeft){
            for(int j = C-1; j>=0; j--){
                if(board[i][j] == 'x') {
                    board[i][j] = '.';
                    return;
                }
            }
        }
        else{
            for(int j = 0; j<C; j++){
                if(board[i][j] == 'x') {
                    board[i][j] = '.';
                    return;
                }
            }
        }
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                sb.append(board[i][j]);
            }
            if(i == R-1)
                continue;
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for(int i =0; i<R; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<C; j++){
                board[i][j] = temp[j];
            }
        }
        N = Integer.parseInt(br.readLine());
        boolean isLeft = true;
        st = new StringTokenizer(br.readLine());
        while(N-->0){
            //1. 막대던지기
            int h = Integer.parseInt(st.nextToken());
            shooting(h, isLeft);
            isLeft = !isLeft;

            //2. 바닥에 붙은거 구분하기
            boolean[][] touchdown = new boolean[R][C];
            Queue<int[]> q = new ArrayDeque<>();
            for(int j =0; j<C; j++){
                if(board[R-1][j] == 'x'){
                    q.add(new int[]{R-1,j});
                    touchdown[R-1][j] = true;
                }
            }
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int ci = cur[0];
                int cj = cur[1];
                for(int d = 0; d<4; d++){
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if(ni < 0 || nj < 0 || ni >= R || nj >= C)
                        continue;
                    if(!touchdown[ni][nj] && board[ni][nj] == 'x'){
                        touchdown[ni][nj] = true;
                        q.add(new int[]{ni,nj});
                    }
                }
            }

            //3. x인데 바닥에 안붙은거 float에 넣기
            ArrayList<int[]> floats = new ArrayList<>();
            for(int i = 0; i<R; i++){
                for(int j = 0; j<C; j++){
                    if(board[i][j] == 'x' && !touchdown[i][j])
                        floats.add(new int[]{i,j});
                }
            }

            if(floats.isEmpty())
                continue;

            //4. mindrop 갱신하기
            int minDrop = 101;
            for(int[] p : floats){
                int ci = p[0];
                int cj = p[1];
                int ni = ci+1;
                // BUG!! 여기서 bound 에러나는데 ni 문제같음
                // 해결 : ni<R을 먼저 써줘야하고 아래가 touchdown 상태가 아니거나 board에서 .일때 증가시켜야함
                while(ni < R && (board[ni][cj] == '.' || !touchdown[ni][cj]))
                    ni++;
                ni -= (ci+1);
                minDrop = Math.min(minDrop, ni);
            }

            //5. board에서 mindrop만큼 떨어뜨리기
            for(int[] p : floats){
                board[p[0]][p[1]] = '.';
            }
            for(int[] p : floats){
                board[p[0]+minDrop][p[1]] = 'x';
            }
        }
        print();
    }
}