
import java.util.*;
import java.io.*;

public class Main {
    private static int N,K;
    private static int[] dist;
    private static int size;
    private static int[] parent;
    private static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);
        dist[N] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == K)
                break;

            int n = cur + 1;
            if(n > -1 && n < size){
                if(dist[n] > dist[cur] + 1){
                    dist[n] = dist[cur] + 1;
                    parent[n] = cur;
                    q.add(n);
                }
            }
            n = cur - 1;
            if(n > -1 && n < size){
                if(dist[n] > dist[cur] + 1){
                    dist[n] = dist[cur] + 1;
                    parent[n] = cur;
                    q.add(n);
                }
            }
            n = cur * 2;
            if(n > -1 && n < size){
                if(dist[n] > dist[cur] + 1){
                    dist[n] = dist[cur] + 1;
                    parent[n] = cur;
                    q.add(n);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = 200001;
        dist = new int[size];
        parent = new int[size];
        for(int i = 0; i<size; i++){
            parent[i] = i;
        }
        Arrays.fill(dist, 100001);

        bfs();

        int time = dist[K];
        sb.append(time).append("\n");

        Stack<Integer> s = new Stack<>();
        int idx = K;
        while(true){
            if(idx == N)
                break;

            s.add(idx);
            idx = parent[idx];
        }
        s.add(N);

        while(!s.isEmpty()){
            sb.append(s.pop()).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
