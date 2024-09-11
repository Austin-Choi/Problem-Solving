import java.io.*;
import java.util.StringTokenizer;

/*
n m 배열에서 1로 된 가장 큰 정사각형의 크기
오른쪽 아래를 기준으로
-1,0 / 0,-1 / -1,-1 중의 최소값에 +1 한게 정사각형의 최대 변 크기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] dp = new int[1001][1001];
        int[][] board = new int[1001][1001];
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = -1;
        for(int i = 1; i<=N; i++){
            String[] temp = br.readLine().split("");
            for(int j = 1; j<=M; j++){
                board[i][j] = Integer.parseInt(temp[j-1]);
                dp[i][j] = board[i][j];
                if(dp[i-1][j-1] >0 && dp[i][j-1] > 0 && dp[i-1][j] > 0 && dp[i][j] > 0)
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                if (dp[i][j] > answer)
                    answer = dp[i][j];
            }
        }
        bw.write((answer*answer)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
