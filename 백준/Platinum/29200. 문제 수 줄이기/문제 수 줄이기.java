/*
N은 최대 20만
연속된 구간으로 분할,, 인접한 구간의 크기는 서로 다름
1 2 1 2 1 2 1 2??
dp[i][len] = i번째까지 봤을때 마지막 구간 길이가 len일 때 xor 합 최대값
10^9 제한으로 xor 값은 2^30가지
서로 독립적인 변화가 30번
-> 긴 구간 하나보다 짧은 구간 여러개의 합이 더 클 것

dp top1, top2 저장하고 top1이 나온 이전 구간크기 저장해서
인접한 크기만 제외되니까 현재크기가 인접크기면 top2쓰고 아니면 top1
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static long[] A;
    static long[] px;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N+1];
        px = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            A[i] = Long.parseLong(st.nextToken());
            px[i] = px[i-1] ^ A[i];
        }

        int B = (int) Math.sqrt(N) + 2;

        long[][] dp = new long[N+1][B+1];
        for(long[] r : dp)
            Arrays.fill(r,-1);

        // 이전 i-len 상태를 기억해서 거기서 b1, b2, l1 값 가져와야함
        // -> 배열로 따로 뺌
        long[] b1 = new long[N+1];
        long[] b2 = new long[N+1];
        int[] l1 = new int[N+1];

        dp[0][0] = 0;
        b1[0] = 0;
        b2[0] = -1;
        l1[0] = 0;

        for(int i = 1; i<=N; i++){
            for(int l = 1; l<=B; l++){
                // 이전
                int k = i-l;
                if(k<0)
                    break;
                long prevB = (l == l1[k]) ? b2[k] : b1[k];
                if(prevB < 0)
                    continue;

                long seg = px[i] ^ px[i-l];
                dp[i][l] = Math.max(dp[i][l], prevB + seg);
            }

            long bb1 = -1;
            long bb2 = -1;
            int ll1 = -1;

            for(int l = 1; l<=B; l++){
                long v = dp[i][l];
                if(v > bb1){
                    bb2 = bb1;
                    bb1 = v;
                    ll1 = l;
                }
                else if(v > bb2)
                    bb2 = v;
            }
            b1[i] = bb1;
            b2[i] = bb2;
            l1[i] = ll1;
        }

        long ans = 0;
        for(int l = 1; l<=B; l++){
            ans = Math.max(ans, dp[N][l]);
        }

        System.out.print(ans);
    }
}
