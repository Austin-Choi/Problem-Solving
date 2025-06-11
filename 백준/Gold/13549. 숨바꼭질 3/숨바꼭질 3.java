import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static int[] dist;
    private static boolean[] visited;

    private static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            int next;
            next = cur * 2;
            if(next > -1 && next < 100001 && !visited[next]){
                visited[next] = true;
                dist[next] = Math.min(dist[next], dist[cur]);
                q.add(next);
            }
            next = cur - 1;
            if(next > -1 && next < 100001 && !visited[next]){
                visited[next] = true;
                dist[next] = Math.min(dist[next], dist[cur]+1);
                q.add(next);
            }
            next = cur + 1;
            if(next > -1 && next < 100001 && !visited[next]){
                visited[next] = true;
                dist[next] = Math.min(dist[next], dist[cur]+1);
                q.add(next);
            }

        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[100001];
        Arrays.fill(dist, 100001);
        dist[N] = 0;
        visited = new boolean[100001];

        bfs(N);

        bw.write(String.valueOf(dist[K]));
        bw.flush();
        bw.close();
        br.close();
    }
}
