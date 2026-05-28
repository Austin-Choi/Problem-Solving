import java.util.*;
import java.io.*;

/*
모든 가능한 경로에 대해서 최대 경로 합 구하기
dp[i] = i의 서브트리에 있는 경로 중 최대 경로 합
초깃값 자기 자신,
루트는 1번
트리의 지름 느낌으로 접근
dfs(u) = u에서 시작해서 아래방향으로만 갈 때 갈 수 있는 최대 경로 합
자식중에 한개만 골라야 함 그중에 큰 것만
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<Integer>[] g;
    static int[] A;
    // 가중치 음수라서 가능한 최소값 계산해서 잡기
    static int ans = -1000 * 30_000 - 1;

    static int dfs(int ci, int parent){
        int best1 = 0;
        int best2 = 0;

        for(int n : g[ci]){
            if(n == parent)
                continue;
            
            int child = dfs(n, ci);
            if(child > best1){
                best2 = best1;
                best1 = child;
            }
            else if(child > best2){
                best2 = child;
            }
        }
        // 자식 다 돌고 밖에서 해야함
        // 가중치가 음수일때 처리해야함 !!
        ans = Math.max(ans, A[ci] + Math.max(0, best1) + Math.max(0, best2));
        return A[ci] + Math.max(0, best1);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        A = new int[N+1];
        for(int i =1; i<=N; i++){
            A[i] = read();
        }

        // dp = new int[N+1];
        dfs(1,-1);
        System.out.print(ans);
    }
}