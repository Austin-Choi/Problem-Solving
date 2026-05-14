import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] g;
    static int[] parent;

    static void find(){
        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while(!q.isEmpty()){
            int ci = q.poll();
            for(int n : g[ci]){
                if(visited[n])
                    continue;
                visited[n] = true;
                parent[n] = ci;
                q.add(n);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        g = new ArrayList[N+1];
        parent = new int[N+1];

        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        int M = N-1;
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        find();
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i<=N; i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.print(sb);
    }
}