import java.util.*;
import java.io.*;

/*
막대기 총 길이가 N이고 그걸 나눴을때 가치가 주어짐
총 합이 정확히 N이라면 안에서 뭘 쓰든 여튼 그거 가치 최대화를 시키면 됨
dp[i] = 길이 합이 i일때 최대 가치
같은걸 여러번 써도 됨.
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int INF = -(100 * 10_000 + 1);
    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i][0] = i+1;
            A[i][1] = read();
        }

        int[] dp = new int[N+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i<N; i++){
            int cw = A[i][0];
            int cv = A[i][1];
            for(int j = cw; j<=N; j++){
                if(dp[j-cw] != INF){
                    dp[j] = Math.max(dp[j], dp[j-cw] + cv);
                }
            }
        }
        System.out.print(dp[N]);
    }
}