import java.util.*;
import java.io.*;

/*
target = tot/4
t에서 한번 자르고 2*t에서 자르고 3*t에서 자르기

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
        long tot = 0;
        for(int i = 0; i<N; i++){
            A[i] = read();
            tot += A[i];
        }

        long T = tot/4;

        long[] p = new long[N+1];
        for(int i = 0; i<N; i++){
            p[i+1] = p[i] + A[i];
        }

        long[] dp = new long[4];
        dp[0] = 1;

        // 큰 단계부터 갱신해서 같은 위치 두번 안쓰이게 함.
        // 0-1배낭 느낌
        for(int i = 1; i<N; i++){
            if(p[i] == 3*T)
                dp[3] += dp[2];
            if(p[i] == 2*T)
                dp[2] += dp[1];
            if(p[i] == T)
                dp[1] += dp[0];
        }
        System.out.print(dp[3]);
    }
}