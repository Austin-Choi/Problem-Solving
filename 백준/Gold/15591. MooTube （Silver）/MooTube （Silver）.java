import java.util.*;
import java.io.*;
public class Main {
    static int N, Q;
    static class Info{
        int node;
        int usado;
        public Info(int n, int u){
            this.node = n;
            this.usado = u;
        }
    }
    static ArrayList<Info>[] board;
    static boolean[] visited;
    static int bfs(int start, int k){
        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[N+1];
        q.add(start);
        visited[start] = true;
        int ans = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(Info next : board[cur]){
                if(visited[next.node])
                    continue;
                if(next.usado >= k){
                    visited[next.node] = true;
                    ans++;
                    q.add(next.node);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[s].add(new Info(e,c));
            board[e].add(new Info(s,c));
        }

        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int ans = bfs(v,k);
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
