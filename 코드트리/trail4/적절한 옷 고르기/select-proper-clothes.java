import java.util.*;
import java.io.*;

/*
dp[m][j] = m일째에 마지막으로 고른 옷이 j일때 화려함의 최대 합


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[][] A = new int[N][3];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read(), read()};
        }

        int[][] dp = new int[M+1][N+1];
        for(int i = 0; i<=M; i++){
            Arrays.fill(dp[i], -1);
        }

        for(int m = 1; m<=M; m++){
            // 오늘 입을 것 i
            for(int i = 0; i<N; i++){
                int s = A[i][0];
                int e = A[i][1];
                if(s > m || e < m)
                    continue;
                // 첫날은 그 전날이 없음
                if(m == 1){
                    dp[m][i] = 0;
                    continue;
                }
                // 어제 입은 것 k
                for(int k = 0; k<N; k++){
                    if(dp[m-1][k] == -1)
                        continue;
                    // i==k 조건은 같은옷 여러번 못입을때만
                    dp[m][i] = Math.max(dp[m][i], dp[m-1][k] + Math.abs(A[i][2] - A[k][2]));
                }
            }
        }
        
        int ans = 0;
        for(int i = 0; i<N; i++){
            ans = Math.max(ans, dp[M][i]);
        }
        System.out.print(ans);
    }
}