import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1025][1025];
        // 초기값 안줘도 됨. 합구하는거고 어차피 0으로 초기화됨
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                if(j == 1){
                    dp[i][j] = dp[i-1][N] + Integer.parseInt(st.nextToken());
                    continue;
                }
                dp[i][j] = dp[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++){
            int total = 0;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int x = x1; x <= x2; x++){
                if(y1-1 == 0){
                    total += dp[x][y2] - dp[x-1][N];
                    continue;
                }
                total += dp[x][y2] - dp[x][y1-1];
            }
            bw.write(total+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}