import java.util.*;
import java.io.*;

// 길이가 N이고 인접 두 원소가 다른 횟수가 M이하일때 기준 수열과의 최대 유사도 값

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
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int[][][] dp = new int[N][5][M+1];
        // 초깃값 -> 1~4 중에 A[0]이랑 같은거 있으면 +1
        for(int i = 1; i<=4; i++){
            if(i == A[0]){
                dp[0][i][0] = 1;
            }
            else
                dp[0][i][0] = 0;
        }

        for(int i = 1; i<N; i++){
            for(int prev = 1; prev<=4; prev++){
                for(int m = 0; m<=M; m++){
                    for(int j = 1; j<=4; j++){
                        int nm = m;
                        // prev랑 다르면 m증가
                        if(prev != j)
                            nm++;
                        // 다음 m 제한 벗어남
                        if(nm > M)
                            continue;
                        
                        // 그 전 최대 유사도
                        int val = dp[i-1][prev][m];
                        // 이번 선택값이 기준 수열과 같으면 유사도+1
                        if(j == A[i])
                            val++;

                        dp[i][j][nm] = Math.max(dp[i][j][nm], val); 
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 1; i<=4; i++){
            for(int m = 0; m<=M; m++){
                ans = Math.max(ans, dp[N-1][i][m]);
            }
        }
        System.out.print(ans);
    }
}