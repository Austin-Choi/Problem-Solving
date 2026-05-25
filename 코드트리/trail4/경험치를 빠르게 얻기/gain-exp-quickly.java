import java.util.*;
import java.io.*;

/*
N = 100, t = 100이고 각 한번만 사용해야함
걸리는 시간이 최소가 된다는건 같은 시간에 경험치가 최대가 된다는것
dp [ w ]  = w 시간이 걸렸을때 최대 경험치(val)
정답은 M 이상이 나오는 최초 i 출력
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
        int[][] A = new int[N][2];
        // 모든 시간의 합 -> dp 크기
        int tot = 0;
        for(int i = 0; i<N; i++){
            // val, weight
            int val = read();
            int weight = read();
            A[i] = new int[]{val, weight};
            tot += weight;
        }

        long[] dp = new long[tot+1];
        long INF = -1;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i<N; i++){
            int cw = A[i][1];
            int cv = A[i][0];
            for(int j = tot; j >= cw; j--){
                if(dp[j-cw] == INF)
                    continue;
                dp[j] = Math.max(dp[j], dp[j-cw]+cv);
            }
        }

        int ans = -1;
        for(int i = 0; i<dp.length; i++){
            if(dp[i] >= M){
                ans = i;
                break;
            }
        }
        System.out.print(ans);
    }
}