import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] times;
    private static ArrayList<Integer>[] board;
    private static int[] indegree;
    private static int[] dist;
    private static void topologySort(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i<=N; i++){
            if(indegree[i] ==  0) {
                q.add(i);
                dist[i] = times[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int n : board[cur]){
                dist[n] = Math.max(dist[n], dist[cur]+times[n]);
                if(--indegree[n] == 0)
                    q.add(n);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        times = new int[N+1];
        board = new ArrayList[N+1];
        indegree = new int[N+1];
        dist = new int[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int n = 0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> inputs = new ArrayList<>();
            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if(num == -1)
                    break;
                inputs.add(num);
            }

            times[n+1] = inputs.get(0);
            for(int i = 1; i<inputs.size(); i++){
                board[inputs.get(i)].add(n+1);
                indegree[n+1]++;
            }
        }

        topologySort();

        for(int i = 1; i<=N; i++){
            bw.write(String.valueOf(dist[i]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
