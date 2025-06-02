import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static ArrayList<Integer>[] board;

    private static int bfs(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();

        dist[start] = 0;
        q.add(start);

        int sum = 0;

        while(!q.isEmpty()){
            int curNode = q.poll();

            for(int next : board[curNode]){
                if(dist[next] == -1){
                    dist[next] = dist[curNode] + 1;
                    sum += dist[next];
                    q.add(next);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }

        int minSum = Integer.MAX_VALUE;
        int minNode = Integer.MAX_VALUE;

        for(int i = 1; i<=N; i++){
            int sum = bfs(i);
            if(sum < minSum){
                minSum = sum;
                minNode = i;
            }
            else if(sum == minSum && i < minNode){
                minNode = i;
            }
        }

        sb.append(minNode);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}