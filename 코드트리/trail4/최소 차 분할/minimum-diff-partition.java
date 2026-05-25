import java.util.*;
import java.io.*;

/*
두개를 둘다 하려면 좀 그러니까 
하나를 결정하면 나머지 하나가 자동으로 결정되므로 
total을 계산해가지고 나머지 하나만 N-1개까지 고르고 
현재 합, total - 현재 합
abs(total - 2*현재 합) 을 최소화 해야함 
weight은 1, val은 입력값

하나씩만 쓰기 -> 역방향

dp[i] = 현재 i개 골랐을때 현재합과 total-현재 합의 최소 차이
이걸 하려면 음.. 
dp[i][j] = i개 골랐을때 합이 j가 가능한지 보기
가능한 j에 대해서 abs(total - 2*j) 를 min으로 갱신
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        int tot = 0;
        for(int i =0 ; i<N; i++){
            A[i] = read();
            tot += A[i];
        }

        // i개를 선택했을때 합이 j가 가능한가?
        // boolean[][] dp1 = new boolean[N+1][tot+1];
        // dp1[0][0] = true;

        // for(int i = 0; i<N; i++){
        //     int cur = A[i];
        //     // 갯수 역순
        //     for(int cc = N; cc >= 1; cc--){
        //         // 가능한 합 역순
        //         for(int j = tot-cur; j>=0; j--){
        //             // cur을 선택하지 않은 이전 상태가 가능하면 (cc-1개, 총합은 j)
        //             // 다음 상태 (cc : cur 선택함 (cc인건 역순이라), 총합은 j + cur)
        //             if(dp1[cc-1][j]){
        //                 dp1[cc][j+cur] = true;
        //             }
        //         }
        //     }
        // }

        // 압축하면 이렇게 가능
        // dp[i]= 합 i 가 가능한지
        boolean[] dp = new boolean[tot+1];
        dp[0] = true;

        for(int i = 0; i<N; i++){
            int cur = A[i];
            for(int j = tot; j >= cur; j--){
                dp[j] |= dp[j-cur];
            }
        }

        int ans = 100 * 1000 + 1;
        for(int j = 0; j<=tot; j++){
            if(dp[j])
                ans = Math.min(ans, Math.abs(tot - (2*j)));
        }
        System.out.print(ans);
    }
}