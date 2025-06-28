
import java.io.*;
import java.util.*;

class Info implements Comparable<Info>{
    int dest;
    int cost;

    public Info(int d, int c){
        this.dest = d;
        this.cost = c;
    }

    @Override
    public int compareTo(Info o){
        return this.cost - o.cost;
    }
}

public class Main {
    private static int N;
    private static int M;
    private static ArrayList<Info>[] board;
    private static boolean[] visited;
    private static int prim(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0));

        int ans = 0;
        int maxEdge = 0;

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cD = cur.dest;
            int cC = cur.cost;

            if(!visited[cD]){
                visited[cD] = true;
                ans += cC;
                maxEdge = Math.max(maxEdge, cC);

                for(Info i : board[cD]){
                    if(!visited[i.dest])
                        pq.add(i);
                }
            }
        }

        return ans - maxEdge;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            board[s].add(new Info(d,c));
            board[d].add(new Info(s,c));
        }

        int ans = prim();

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}