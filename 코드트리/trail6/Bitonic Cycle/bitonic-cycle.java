import java.util.*;
import java.io.*;

/*
1->2->4->3->1
증가했다가 감소하는 경로를 원형이라 생각하고 양쪽에서 찢어서 보면
두개의 단조 증가하는 경로로 나눌 수 있음
dp[i][j] = 0~max(i,j)까지의 정점을 왼쪽과 오른쪽 경로에 한번씩 배정하고
0번부터 i까지가 왼쪽 경로 0번부터 j까지가 오른쪽 경로
전개는
i에 max(i,j)+1을 붙이기, j에 max(i,j)+1을 붙이기 둘중 하나
-> dp의 정의를 유지하기 위해서는 새로운 점을 하나만 추가해야함.
-> i<j를 항상 유지해야 함

그럼 정답은 dp[N-2][N-1] -> N-1까지의 정점을 한번씩 배정했고 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] pos;
    static final long INF = Long.MAX_VALUE / 4;

    static long getDist(int u, int v){
        int x1 = pos[u][0];
        int y1 = pos[u][1];
        int x2 = pos[v][0];
        int y2 = pos[v][1];

        long a = (long) (y1-y2);
        long b = (long) (x1-x2);
        return a*a + b*b;
    }


    public static void main(String[] args) throws IOException{
        N = read();
        pos = new int[N][2];
        for(int i = 0; i<N; i++){
            pos[i] = new int[]{read(), read()};
        }
        // 입력에서 x 좌표가 모두 다르다햇음
        Arrays.sort(pos, (a,b)->{
            return a[0] - b[0];
        });

        long[][] dp = new long[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], INF);
        }

        /*
        i<j를 항상 유지하면 끝점은 항상 j+1
        오른쪽에 붙이면 dp[i][j+1] 갱신
        왼쪽에 붙이면 dp[j][j+1] 갱신
        */
        dp[0][1] = getDist(0,1);
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(i >= j)
                    continue;
                if(dp[i][j] == INF)
                    continue;
                if(Math.max(i,j) == N-1)
                    continue;
                
                // 현재 dp [i][j]
                // 오른쪽에 붙이기
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+ getDist(j, j+1));
                // 왼쪽에 붙이기
                dp[j][j+1] = Math.min(dp[j][j+1], dp[i][j] + getDist(i, j+1));
            }
        }

        long ans = INF;
        for(int i = 0; i<N-1; i++){
            ans = Math.min(ans, dp[i][N-1] + getDist(i,N-1));
        }
        System.out.print(ans);
    }
}