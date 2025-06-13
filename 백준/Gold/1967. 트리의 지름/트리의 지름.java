
import java.io.*;
import java.util.*;

class Info
{
    public int node;
    public int cost;
    public Info(int node, int cost){
        this.cost = cost;
        this.node = node;
    }
}

public class Main {
    private static int N;
    private static boolean[] visited;
    private static ArrayList<Info>[] board;
    private static int[] dist;
    private static void dfs(int start, int sum){
        for(Info i : board[start]){
            int nextNode = i.node;
            int nextCost = i.cost;

            if(!visited[nextNode]){
                visited[nextNode] = true;
                dist[nextNode] = sum+nextCost;
                dfs(nextNode, dist[nextNode]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int ans;

        if(N!=1){
            board = new ArrayList[N+1];
            for(int n = 1; n<=N; n++){
                board[n] = new ArrayList<>();
            }

            for(int n = 0; n<N-1; n++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                board[start].add(new Info(end, cost));
                board[end].add(new Info(start, cost));
            }

            visited = new boolean[N+1];
            visited[1] = true;
            dist = new int[N+1];
            dfs(1,0);

            int maxtp = 0;
            int start = 0;
            for(int n = 1; n<=N; n++){
                if(maxtp < dist[n]) {
                    maxtp = dist[n];
                    start = n;
                }
            }

            visited = new boolean[N+1];
            visited[start] = true;
            dist = new int[N+1];
            dfs(start, 0);

            ans = 0;
            for(int n = 1; n<=N; n++){
                if(ans < dist[n])
                    ans = dist[n];
            }
        }
        else
            ans = 0;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}