/*
파일을 계속 두 개씩 합쳐서 소설의 여러 장들이 연속이 되도록 파일을 합쳐나가고
-> 인접한 두개만 가능,
-> 인접 두개와 전체 합을 합쳐야
dp[i][j] = i~j까지 합치기 비용
dp[i][j] = min(dp[i][cur] + dp[cur+1][j]) + (prefix[j+1]-prefix[i]);
dp[i][i] = 0
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-->0){
            int K = Integer.parseInt(br.readLine());
            long[] in = new long[K];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<K; i++) {
                long temp = Long.parseLong(st.nextToken());
                in[i] = temp;
            }

            long[] prefix = new long[K+1];
            for(int i = 0; i<K; i++)
                prefix[i+1] = prefix[i] + in[i];

            long[][] dp = new long[K][K];

            for(int len = 2; len <= K; len++){
                for(int i = 0; i+len - 1 <K; i++){
                    int j = i + len -1;
                    dp[i][j] = Long.MAX_VALUE/2;
                    for(int cur = i; cur < j; cur++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][cur] + dp[cur+1][j]);
                    }
                    dp[i][j] += prefix[j+1] - prefix[i];
                }
            }

            sb.append(dp[0][K-1]).append("\n");
        }
        System.out.print(sb);
    }
}
