import java.io.*;
import java.util.*;

class Info{
    int dest;
    int cost;

    public Info(int d, int c){
        this.dest = d;
        this.cost = c;
    }
}

public class Main {
    private static int N;
    private static int K;
    private static int W;
    private static int[] times;
    private static ArrayList<Info>[] board;
    private static int[] inDegree;

    private static int sol(){
        int[] dp = new int[N+1];
        Queue<Info> q = new ArrayDeque<>();
        // 진입차수 0인거 다 넣어주기
        for(int i = 1; i<inDegree.length; i++){
            if(inDegree[i] == 0){
                q.add(new Info(i, times[i]));
                dp[i] = times[i];
            }
        }

        while(!q.isEmpty()){
            Info cur = q.poll();

            for(Info n : board[cur.dest]){
                dp[n.dest] = Math.max(dp[n.dest], dp[cur.dest] + n.cost);
                if(--inDegree[n.dest] == 0)
                    q.add(n);
            }
        }
        
        return dp[W];
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            times = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<=N; i++){
                times[i] = Integer.parseInt(st.nextToken());
            }

            board = new ArrayList[N+1];
            inDegree = new int[N+1];
            for(int i = 1; i<=N; i++){
                board[i] = new ArrayList<>();
            }
            for(int k = 0; k<K; k++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                board[s].add(new Info(e, times[e]));
                // 진입차수 설정
                inDegree[e]++;
            }

            W = Integer.parseInt(br.readLine());

            bw.write(sol()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
