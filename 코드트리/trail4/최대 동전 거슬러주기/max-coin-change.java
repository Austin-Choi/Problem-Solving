import java.util.*;
import java.io.*;

//dp[i] = i원을 거슬려줄 수 있는 최대 동전의 수
// 이전 상태는 dp[i - coin]

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        
        int[] A = new int[N];
        for(int i= 0; i<N; i++){
            A[i] = read();
        }
        //Arrays.sort(A);

        int[] dp = new int[M+1];
        int INF = M / A[0] + 2;
        Arrays.fill(dp, -INF);
        dp[0] = 0;

        for(int i =0 ; i<N; i++){
            int cur = A[i];
            for(int j = cur; j<=M; j++){
                if(dp[j-cur] != -INF)
                    dp[j] = Math.max(dp[j], dp[j-cur]+1);
            }
        }
        System.out.print(dp[M] < 0 ? -1 : dp[M]);
    }
}