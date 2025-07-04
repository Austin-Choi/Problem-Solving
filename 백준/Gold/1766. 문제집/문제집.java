
import java.util.*;
import java.io.*;

public class Main {
    private static int N, M;
    private static ArrayList<Integer>[] board;
    private static int[] indegree;
    private static Queue<Integer> ans = new ArrayDeque<>();
    private static void topologySort(){
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);

            for(int next : board[cur]){
                if(--indegree[next] == 0)
                    q.add(next);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        indegree = new int[N+1];

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            board[s].add(e);
            indegree[e]++;
        }

        topologySort();

        for(int num : ans){
            bw.write(num+" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
