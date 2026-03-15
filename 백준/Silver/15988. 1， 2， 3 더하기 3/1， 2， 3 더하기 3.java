
/*
상태가 3개니까 벡터로 표현하면 O(3^3 log N)이라서 단일 항목에서는 빠를수도
T때문에 dp 전처리가 정석일듯

벡터로 나타내면
i, i-1, i-2일때
i+1 = i+1, i, i-1
1행 : 1 1 1
2행 : 1 0 0
3행 : 0 1 0
Vn+1 = 위의 행렬 * Vn
V3부터 시작 dp[3]까지 정의하고
 */
import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i<=1000000; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb);
    }
}
