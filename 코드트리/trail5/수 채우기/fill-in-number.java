import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] arr = new int[]{5,2};
        int[] dp = new int[N+1];
        Arrays.fill(dp, N+1);
        dp[0] = 0;

        for(int i = 0; i<2; i++){
            int cv = arr[i];
            for(int j = cv; j<=N; j++){
                dp[j] = Math.min(dp[j], dp[j-cv]+1);
            }
        }
        System.out.print(dp[N] == N+1 ? -1 : dp[N]);
    }
}