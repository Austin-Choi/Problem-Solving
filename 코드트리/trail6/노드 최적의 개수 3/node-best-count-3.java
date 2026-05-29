import java.util.*;
import java.io.*;

/*
물건을 놓는다... 선택한다?
간선의 양 끝 점중 최소 하나는 물건이 올라가 있어야 한다. 
min dominating set on tree

dp[u][3] = u를 루트 노드로 갖는 서브트리 내에서 조건을 만족하는 선택된 최소 노드 갯수

부모-현재-자식
dp[u][0] = 부모 o, 현재 x, 자식 x -> 자식에서 안 뽑혀도 상관없음 (부모 커버)
dp[u][1] = 부모 x, 현재 x, 자식 o -> 자식이 최소한 하나는 필요함 (자식이 커버해야)
dp[u][2] = 부모 x, 현재 o, 자식 x -> 나하나로 충분 (내가 커버)

dp[u][0] += min(dp[v][1], dp[v][2])
-> 자식 입장에서 부모가 커버쳐주지 않아서 1,2 가능 
dp[u][1] += min(dp[v][1], dp[v][2]) 인데 한번도 [2]가 안나왔으면 loss 중 최소일때 값 더해서 처리
-> 자식 입장에서 부모가 커버쳐주지 않아서 1,2 가능한데 최소한 하나는 [2]여야 함.
dp[u][2] += min(dp[v][0], dp[v][1], dp[v][2])
-> 자식 입장에서 부모가 커버쳐줘서 0,1,2 가능

정답은 루트는 부모가 없으니까 min(dp[1][1], dp[1][2])
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<Integer>[] g;
    static int[][] dp;
    static final int INF = 100_000 * 100_000 + 1;

    // leaf 명시적으로 처리 꼭 하기
    static void dfs(int u, int parent){
        dp[u][2] = 1;

        boolean found = false;
        boolean isLeaf = true;
        int minLoss = INF;

        for(int v : g[u]){
            if(v == parent)
                continue;

            isLeaf = false;
            dfs(v, u);

            dp[u][0] += Math.min(dp[v][1], dp[v][2]);
            dp[u][2] += Math.min(Math.min(dp[v][0], dp[v][1]), dp[v][2]);

            if(dp[v][2] <= dp[v][1]){
                found = true;
                dp[u][1] += dp[v][2];
            }
            else
                dp[u][1] += dp[v][1];
            
            // dp[u][1]에서 [1]만 선택되어서
            // [2]를 선택해야하지만 그 손해가 가장 작을때를 저장
            minLoss = Math.min(minLoss, dp[v][2] - dp[v][1]);
        }
        if(isLeaf){
            dp[u][1] = INF;
            return;
        }

        if(!found)
            dp[u][1] += minLoss;
    }


    public static void main(String[] args) throws IOException{
        N = read();
        int M = N-1;
        g = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            g[i] = new ArrayList<>();
        }    

        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dp = new int[N+1][3];
        dfs(1,-1);
        System.out.print(Math.min(dp[1][1], dp[1][2]));
    }
}