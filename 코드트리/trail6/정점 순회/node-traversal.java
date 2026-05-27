import java.util.*;
import java.io.*;

/*
N, S = root, D 
입력으로 양방향 그래프의 형태로 트리 입력받고 

dist[i] = S로부터 현재 i까지의 거리
-> dfs로 구하고 

dp[i] = i를 루트로 갖는 서브트리내에서 D 초과 거리를 갖는 노드가 있는지
있다면 거기까지는 이동해야 함 
간선의 길이는 항상 1
-> 근데 dist 구한걸로만은 i의 서브트리 내에 무슨 정점이 있는지 모르는데 이걸 어떻게하지
-> 내부 dfs 자체가 서브트리 구간이니까 dfs가 bool 리턴하게끔하면 그게 서브트리의 여부

트리에서 거리가 D 초과인 방향에는 반드시 리프 노드가 존재함.
----------------------------------------

현재 위치에서 얼마나 D만큼 내려가야 하는가 -> 서브트리의 깊이

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,S,D;
    static ArrayList<Integer>[] g;
    // static int[] dist;
    static int ans = 0;

    // 현재 노드에서 서브트리 내 가장 깊은 리프까지 거리 반환하기
    static int dfs(int ci, int parent){
        int maxD = 0;
        boolean leaf = true;

        for(int n : g[ci]){
            if(n == parent)
                continue;

            leaf = false;

            int childD = dfs(n, ci) + 1;
            // 자식 거리가 D 넘어가면 그 자식까지는 가야함
            if(childD > D){
                // ci->n + n->ci
                ans += 2;
            }
            maxD = Math.max(maxD, childD);
        }

        // 리프노드면 갈 곳 없음. 거리 0
        if(leaf)
            return 0;

        return maxD; 
    }

    public static void main(String[] args) throws IOException{
        N = read();
        S = read();
        D = read();
        g = new ArrayList[N+1];
        //dist = new int[N+1];

        for(int i=1; i<=N; i++)
            g[i] = new ArrayList<>();

        for(int i = 0; i<N-1; i++){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }
        
        dfs(S, -1);
        System.out.print(ans);
    }
}