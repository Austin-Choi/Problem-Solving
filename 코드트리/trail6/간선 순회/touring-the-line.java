import java.util.*;
import java.io.*;

/*
문제의 목표는 가장 많은 간선을 지나고 싶어함. 
그래프를 간선을 1로잡고 트리의 지름을 구하면 그게 가장 많은 간선을 지나는 시작점과 끝점이 될거임
대신 처음 시작점은 아무거나로 하고 처음 끝점 1, 다음 끝점 2 따로 구해서 그렇게 구함

그렇게 구한거 시작점, 끝점으로 해서 원래 weight 들어간 그래프에서 
경로 계산

아 근데 이게 단순히 노드, weigth 1로 하면 경로가 여러가지가 될 수 있음. 
음........dfs 파라미터 늘리기???
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, D;
    static boolean[] visited;
    // 간선갯수계산용
    // static ArrayList<Integer>[] g1;
    //거리계산용
    static ArrayList<int[]>[] g;
    static int far = -1;

    // 간선은 최대한 많이, 
    // 날짜수는 최소한으로 해야하니까 거리는 최단거리로 잡아야됨
    static int minDist = Integer.MAX_VALUE;
    static int maxDepth = -1;

    static void dfs(int ci, int depth, int dist){
        if(depth > maxDepth
            || depth == maxDepth && dist < minDist){
            maxDepth = depth;
            minDist = dist;
            far = ci;
        }

        for(int[] n : g[ci]){
            if(visited[n[0]])
                continue;
            visited[n[0]] = true;
            dfs(n[0], depth+1, dist+n[1]);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        D = read();
        g = new ArrayList[N+1];

        for(int i =1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1,0,0);

        visited = new boolean[N+1];
        visited[far] = true;
        dfs(far,0,0);

        int ans = (minDist % D == 0) ? (minDist / D) : (minDist / D)+1;
        System.out.print(ans);
    }
}