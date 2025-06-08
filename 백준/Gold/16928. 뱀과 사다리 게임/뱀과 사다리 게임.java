import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] board;
    private static boolean[] visited;
    private static int[] dist;

    private static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int n = 1; n<=6; n++){
                int next = cur + n;
                if(next < 101) {
                    if (!visited[next]) {
                        visited[next] = true;
                        if (board[next] != 0) {
                            q.add(board[next]);
                            if(dist[board[next]] == 0)
                                dist[board[next]] = dist[cur] + 1;
                            else
                                dist[board[next]] = Math.min(dist[board[next]], dist[cur]+1);
                        } else {
                            q.add(next);
                            if(dist[next] == 0)
                                dist[next] = dist[cur] + 1;
                            else
                                dist[next] = Math.min(dist[next], dist[cur]+1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new int[101];
        visited = new boolean[101];
        visited[0] = true;
        dist = new int[101];

        for(int n = 0; n<N+M; n++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            board[i] = j;
        }

        bfs(1);

        bw.write(String.valueOf(dist[100]));
        bw.flush();
        bw.close();
        br.close();
    }
}
