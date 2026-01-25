/*
dp[i] = in[i]를 마지막으로 선택했을때 합의 최대값

dp[i] = input[i];
in = 1 100 2 50 60 3 5 6 7 8
dp = 1 101 3 53 113 6 11 17 24 32
113
-> i보다 작은 모든 j에 대해서 in[j] < in[i] 인 것들에서
dp[j]가 최대인 값을 찾았으면 in[i]를 더해주기
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] in = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            in[i] = Integer.parseInt(st.nextToken());
            dp[i] = in[i];
        }

        for(int i = 1; i<N; i++){
            int max = 0;
            for(int j = 0; j<i; j++)
                if(in[j] < in[i] && dp[j] >= max)
                    max = dp[j];
            //if(max != 0)
            dp[i] = max + in[i];
        }

        int max = 0;
        for(int i : dp){
            max = Math.max(max, i);
        }
        System.out.print(max);
    }
}
