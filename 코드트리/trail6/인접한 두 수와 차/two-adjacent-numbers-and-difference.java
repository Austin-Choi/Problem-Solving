import java.util.*;
import java.io.*;

/*
인접한 두 수를 고르게 되면 두 수의 합 만큼의 점수를 얻게 되고 
두 수가 사라짐과 동시에 해당 위치에 두 수의 차만큼에 해당하는 새로운 수가 추가

dp[i][j][v] = 구간 [i,j]를 처리했을 때 새로운 수 v가 추가될 때 얻을 수 있는 최대 점수
dp[i][k][lv] , dp[k+1][j][rv] 가 모두 유효할때
dp[i][j][abs(lv-rv)] = max(dp[i][k][lv] + dp[k+1][j][rv] + lv + rv)
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

        int[][][] dp = new int[N][N][11];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        for(int i = 0; i<N; i++){
            dp[i][i][arr[i]] = 0;
        }

        for(int len = 2; len <=N; len++){
            for(int i = 0; i+len-1<N; i++){
                int j = i+len-1;
                for(int k = i; k<j; k++){
                    for(int lv = 0; lv<=10; lv++){
                        if(dp[i][k][lv] == -1)
                            continue;
                        for(int rv = 0; rv <= 10; rv++){
                            if(dp[k+1][j][rv] == -1)
                                continue;
                            int nv = Math.abs(lv-rv);
                            dp[i][j][nv] = Math.max(dp[i][j][nv], dp[i][k][lv] + dp[k+1][j][rv] + lv + rv);
                        }
                    }
                }
            }
        }

        int ans = -1;
        for(int i = 0; i<=10; i++){
            ans = Math.max(ans, dp[0][N-1][i]);
        }
        System.out.print(ans);
    }
}