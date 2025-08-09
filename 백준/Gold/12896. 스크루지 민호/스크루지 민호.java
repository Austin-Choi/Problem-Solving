import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static ArrayList<Integer>[] board;
    static boolean[] visited;
    static int ans = 0;
    static int dfs(int start){
        visited[start] = true;

        int first = 0;
        int second = 0;

        for(int next : board[start]){
            if(!visited[next]) {
                int dist = dfs(next) + 1;
                if(dist > first){
                    second = first;
                    first = dist;
                }
                else if(dist > second){
                    second = dist;
                }
            }
        }
        ans = Math.max(ans, first + second);
        return first;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        board = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int n = 0; n<N-1; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u].add(v);
            board[v].add(u);
        }

        dfs(1);
        ans = (ans + 1) / 2;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
