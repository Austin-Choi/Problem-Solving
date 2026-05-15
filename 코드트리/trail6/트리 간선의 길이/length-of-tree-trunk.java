import java.util.*;
import java.io.*;

/*
그래프 위에서의 최장경로.. 그냥 dfs두번
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<int[]>[] g;
    static boolean[] visited;

    static int maxDist = -1;
    static int farNode = -1;

    // si로부터 시작하는 최장경로에서의 끝점 반환
    static void dfs(int ci, int dist){
        if(dist > maxDist){
            maxDist = dist;
            farNode = ci;
        }

        for(int[] n : g[ci]){
            int ni= n[0];
            int nd = n[1];
            if(visited[ni])
                continue;
            visited[ni] = true;
            dfs(ni, dist + nd);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int n = 1; n<=N; n++){
            g[n] = new ArrayList<>();
        }

        int M = N-1;
        int root = 10001;
        while(M-->0){
            int u = read();
            root = Math.min(root, u);
            int v = read();
            int w = read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        visited = new boolean[10001];
        visited[root] = true;
        dfs(root, 0);

        visited = new boolean[10001];
        visited[farNode] = true;
        dfs(farNode, 0);

        System.out.print(maxDist);
    }
}