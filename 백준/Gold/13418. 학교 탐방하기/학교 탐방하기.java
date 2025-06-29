
import java.util.*;
import java.io.*;

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
        // 오르막길 우선
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0,-1));
        visited = new boolean[N+1];

        int maxPath = 0;
        while(!pq.isEmpty()){
            Info cur = pq.poll();

            if(!visited[cur.dest]) {
                visited[cur.dest] = true;

                if(cur.cost == 0)
                    maxPath++;

                for (Info i : board[cur.dest]) {
                    pq.add(i);

                }
            }
        }

        // 내리막길 우선
        pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(new Info(0,-1));
        visited = new boolean[N+1];

        int minPath = 0;
        while(!pq.isEmpty()){
            Info cur = pq.poll();

            if(!visited[cur.dest]) {
                visited[cur.dest] = true;

                if(cur.cost == 0)
                    minPath++;

                for (Info i : board[cur.dest]) {
                    pq.add(i);
                }
            }
        }

        return (maxPath*maxPath) - (minPath*minPath);
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 0; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int m = -1; m<M; m++){
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
