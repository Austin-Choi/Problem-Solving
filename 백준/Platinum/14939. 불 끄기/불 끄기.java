/*
각칸은 누르거나 안 누르거나 (최소값 구하는거라 2번이상은 불필요함)
위->아래, i+1줄에서만 i줄 끌 수 있음
-> 같은 j 상에서 i,j가 켜져있으면 i+1,j는 꼭 눌러야함 안그러면 못 끔
맨 윗줄은 그 윗줄이 없어서 아무짓이나 할 수 있음 2^10가지
->
ans 101로 초기화
1) 복사한 board(copy)의 0st 줄 mask는 i대로 하고 변경횟수 합산함
2) 1부터 9줄까지 바로 위가 copy에서 켜져있으면 누르고 횟수에 +1함
3) 9줄만 다꺼져있으면 횟수를 ans에 최소값 갱신
 */
import java.io.*;
public class Main {
    static int[][] board = new int[10][10];
    static final int INF = 101;
    // 값복사해서 씀 state마다 보드 달라서
    static int[][] copy(){
        int[][] rst = new int[10][10];
        for(int i =0; i<10; i++){
            for(int j = 0; j<10; j++){
                rst[i][j] = board[i][j];
            }
        }
        return rst;
    }

    // 십자가누르기
    static int[] di = {0,0,0,-1,1};
    static int[] dj = {0,-1,1,0,0};

    static void press(int[][] b, int ci, int cj){
        for(int d= 0; d<5; d++){
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if(ni < 0 || nj < 0 || ni >= 10 || nj >= 10)
                continue;
            b[ni][nj] ^= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = INF;

        for(int i = 0; i<10; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<10; j++){
                if(temp[j] == 'O')
                    board[i][j] = 1;
            }
        }

        for(int s = 0; s<1024; s++){
            int[][] copyboard = copy();
            int cnt = 0;

            for(int j = 0; j<10; j++){
                //state의 j번째 비트 1이면 누름
                if((s & (1<<j)) != 0){
                    press(copyboard, 0, j);
                    cnt++;
                }
            }

            for(int i = 1; i<10; i++){
                for(int j = 0; j<10; j++){
                    if(copyboard[i-1][j] == 1){
                        press(copyboard, i, j);
                        cnt++;
                    }
                }
            }

            boolean off = true;
            for(int j = 0; j<10; j++){
                if(copyboard[9][j] == 1){
                    off = false;
                    break;
                }
            }

            if(off)
                ans = Math.min(ans, cnt);
        }

        System.out.println(ans == INF ? -1 : ans);
    }
}
