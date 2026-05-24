import java.util.*;
import java.io.*;

/*
백트래킹했다가 TLE나옴

현재 값을 더하기 전에 상태가 존재한다면 현재 값을 더한것도 true임
dp[ i ] = 합이 i가 될 때 존재 여부
차례대로 해야하고 한번씩만 써야 함
-> 역방향, 0/1 배낭
-> 기존에 가능했거나, 현재 값을 쓰기 전에 가능했으면 현재 값을 써도 가능임
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws Exception {
        int N = read();
        int M = read();
        int[] A = new int[N];
        for(int i= 0; i<N; i++)
            A[i] = read();

        boolean[] dp = new boolean[M+1];
        dp[0] = true;

        for(int i = 0; i<N; i++){
            int cur = A[i];
            for(int j = M; j>=cur; j--){
                dp[j] = dp[j] || dp[j-cur];
            }
        }
        System.out.print(dp[M] ? "Yes" : "No");
    }
}