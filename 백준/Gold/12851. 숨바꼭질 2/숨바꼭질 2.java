
import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int K;
    private static final int MAX_LIMIT = 100001;
    private static int[] dist;
    private static int[] count;
    private static void bfs(int idx){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(idx);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : new int[]{cur -1, cur + 1, cur*2}){
                if(next < 0 || next >= MAX_LIMIT)
                    continue;

                // 다음 노드를 처음 방문하는 경우
                if(dist[next] > dist[cur] + 1){
                    dist[next] = dist[cur] + 1;
                    count[next] = count[cur]; // 새로운 최단 경로 수
                    q.add(next);
                }
                // 다음 노드가 이미 같은 시간으로 방문된 경우
                // 경로를 추가함
                else if(dist[next] == dist[cur] + 1){
                    count[next] += count[cur];
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        count = new int[100001];
        Arrays.fill(dist, MAX_LIMIT);

        dist[N] = 0;
        count[N] = 1;
        bfs(N);


        bw.write(dist[K] + "\n");
        bw.write(count[K] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
