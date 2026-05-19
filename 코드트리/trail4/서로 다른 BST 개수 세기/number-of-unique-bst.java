import java.util.*;
import java.io.*;

/*
모든 N은 루트가 될 수 있음
루트값에 따라서 왼쪽 자식이 될수 있는 값의 분포와 오른쪽 자식이 될 수 있는 값의 분포는 달라짐. 
왼쪽 경우의수 * 오른쪽 경우의 수 = 부모?
dp[i] += dp[i-1] * dp[N-i]

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        long[] dp = new long[N+1];
        // 빈 서브트리
        dp[0] = 1; 
        if(N >= 1)
            dp[1] = 1;
        // 최상위 루트 고정해놓고 
        // 내부에서 가능한 루트 전부해보기
        for(int i = 2; i<=N; i++){
            // 왼쪽 결정되면 오른쪽도 자동으로 결정
            for(int r = 1; r<=i; r++){
                dp[i] += dp[r-1] * dp[i-r];
            }
        }
        System.out.print(dp[N]);
    }
}