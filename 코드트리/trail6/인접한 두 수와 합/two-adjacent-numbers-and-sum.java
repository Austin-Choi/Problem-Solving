import java.util.*;
import java.io.*;

/*
인접 두수 최대 점수 구하기 -> 구간합 dp
두 수의 합만큼 해당하는 새로운 수 추가
-> pSum 필요
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[] pSum;
    static int getSum(int u, int v){
        return pSum[v] - pSum[u-1];
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] arr = new int[N+1];
        // [i,j]sum = ps[j] - ps[i-1]
        pSum = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = read();
            pSum[i] = pSum[i-1] + arr[i];
        }

        // dp[i][j] = [i,j] 구간에서 얻을 수 있는 최대 점수
        int[][] dp = new int[N+1][N+1];
        for(int i = 1; i<N; i++){
            dp[i][i+1] = Math.abs(arr[i+1] - arr[i]);
        }

        for(int len = 3; len <= N; len++){
            for(int i = 1; i+len-1<=N; i++){
                int j = i+len-1;
                // [i,k] [k+1,j] 두 구간을 합치게 되니까 
                // sum[k+1,j] - sum[i,k]
                for(int k = i; k<j; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j] + Math.abs(getSum(i,k) - getSum(k+1, j)));
                }
            }
        }
        System.out.print(dp[1][N]);
    }
}