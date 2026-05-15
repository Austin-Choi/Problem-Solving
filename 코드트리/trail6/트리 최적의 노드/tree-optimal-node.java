import java.util.*;
import java.io.*;

/*
다른 모든 정점과의 최댓값이 최소가 되는 노드..
최댓값이 최소가 되려면.. 평탄해야된다는건가
중앙값 구하라는건가?
트리지름 /2??
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<Integer>[] g;
    static boolean[] visited;
    
    static int maxDist = 0;
    static int far = -1;

    static void dfs(int ci, int dist){
        if(dist > maxDist){
            maxDist = dist;
            far = ci;
        }

        for(int n : g[ci]){
            if(visited[n])
                continue;
            visited[n] = true;
            dfs(n, dist+1);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1,0);

        visited = new boolean[N+1];
        visited[far] = true;
        dfs(far,0);

        int ans = (maxDist % 2 != 0 ? maxDist/2+1 : maxDist/2);
        System.out.print(ans);
    }
}