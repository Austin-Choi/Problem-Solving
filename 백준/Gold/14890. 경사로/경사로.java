/*
일단 board로 받고
행, 열로 2N개로 쪼개서 보기
----------------------
diff로 현재칸과 다음칸의 차이를 계산함
(다음칸 - 현재칸)
만약 0이면 continue
slope[j]로 여기에 경사로가 놓였는지 아닌지 체크함 bool
-1이면 내려가는 경우 (앞 방향으로 L 조건 체크)
1이면 올라가는 경우 (뒤 방향으로 L 조건 체크)
그 외면 false

내려가는 경우는
j = i+1로 해서 i+1이랑 j랑 같은지를 i+L까지 정방향 확인함
올라가는 경우는
j = i로 해서 i랑 j랑 같은지 j>i-L까지 거꾸로 확인함

두 경우 전부 저거 외에도
slope있어도 false
다 통과하면 slope[j]를 true로
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, L;
    static int[][] board;
    static int[][] rows;
    static boolean canPass(int[] line){
        // 경사로 설치 여부
        boolean[] slope = new boolean[N];

        for(int i = 0; i<N-1; i++){
            int diff = line[i+1] - line[i];
            if(diff == 0)
                continue;

            if(diff == 1){
                for(int j = i; j > i - L; j--){
                    if(j < 0 || line[j] != line[i] || slope[j])
                        return false;
                    slope[j] = true;
                }
            }
            else if(diff == -1){
                for(int j = i+1; j<= i+L; j++){
                    if(j>=N || line[j] != line[i+1] || slope[j])
                        return false;
                    slope[j] = true;
                }
                // 경사로 끝까지 건너뛰기
                i += L -1;
            }
            else
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rows = new int[2*N][N];
        for(int i = 0; i<N; i++){
            rows[i] = board[i];
        }

        for(int i = N; i<2*N; i++){
            for(int j = 0; j<N; j++){
                rows[i][j] = board[j][i-N];
            }
        }

        int ans = 0;
        for(int n = 0; n<2*N; n++){
            if(canPass(rows[n]))
                ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}