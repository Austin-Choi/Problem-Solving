import java.util.*;
import java.io.*;

/*
dp[l][r] = 구간 l,r에서 끝점 l,r만 남기는 최소 비용

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = read();
        }

        int[][] dp = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 초기화
        for(int i = 0; i<N-1; i++){
            dp[i][i+1] = 0;
        }

        // 기저가 i,i+1 길이 2인 상태라서 3부터 계산하기
        for(int len = 3; len <= N; len++){
            for(int i = 0; i+len-1 < N; i++){
                int j = i+len-1;
                for(int k = i+1; k<j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + (arr[i]*arr[k]*arr[j]));
                }
            }
        }
        System.out.print(dp[0][N-1]);
    }
}