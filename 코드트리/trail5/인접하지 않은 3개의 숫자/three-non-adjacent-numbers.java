import java.util.*;
import java.io.*;

/*
dp[i][k] = i까지 처리했을때, 현재 선택 수가 k일때 최대 합
dp[i][k] = max(dp[i-1][k-1], dp[i-2][k] + A[i])
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
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        // 최대 3개 고르니까
        int[][] dp = new int[N+1][4];
        // 일단 전부 불가능상태 만들고
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }
        // 하나도 안 고른건 0임
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }

        dp[1][1] = A[0];

        for(int i =2; i<=N; i++){
            for(int k = 1; k<=3; k++){
                // 선택하지 않는경우, 현재 선택하는 경우(한칸 건너뛰기)
                dp[i][k] = Math.max(dp[i-1][k], dp[i-2][k-1] + A[i-1]);
            }
        }

        System.out.print(dp[N][3]);
    }
}