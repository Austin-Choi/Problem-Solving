/*
무한배낭 문제 + 그리디 복원
dp[M] = M원으로 만들수 있는 가장 긴 자릿수
왼쪽부터 큰 숫자 우선, 남은 자리수 가능 여부 확인
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        int[] dp = new int[M+1];
        // 비용이 한 숫자의 가격일때 초기화
        for(int i = 1; i<N; i++){
            if(price[i] <= M)
                dp[price[i]] = 1;
        }
        // dp 전개
        for(int cost = 0; cost <= M; cost++){
            for(int j = 0; j<N; j++){
                if(cost + price[j] <= M)
                    dp[cost + price[j]] = Math.max(dp[cost + price[j]], dp[cost]+1);
            }
        }

        int remain = M;
        StringBuilder sb = new StringBuilder();
        int len = dp[M];
        for(int i = 0; i<len; i++){
            for(int d = N-1; d>=0; d--){
                if(i==0 && d==0)
                    continue;
                if(remain >= price[d] && dp[remain-price[d]] == len - i -1){
                    sb.append(d);
                    remain -= price[d];
                    break;
                }
            }
        }
        if(sb.length() == 0)
            System.out.print(0);
        else
            System.out.print(sb);
    }
}
