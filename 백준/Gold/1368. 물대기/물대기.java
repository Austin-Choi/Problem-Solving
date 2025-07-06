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
    private static int[] well;
    private static ArrayList<Info>[] board;
    private static int prim(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+2];
        // 정점 0부터 시작
        // 우물 파기 간선은 board[0]에 있음
        pq.add(new Info(0,0));

        int ans = 0;

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            int cc = cur.cost;

            if(!visited[cd]){
                visited[cd] = true;
                ans += cc;

                for(Info i : board[cd]){
                    if(!visited[i.dest]){
                        pq.add(i);
                    }
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new ArrayList[N+1];
        for(int i = 0; i<=N; i++){
            board[i] = new ArrayList<>();

        }
        well = new int[N+1];
        for(int n = 1; n<=N; n++){
            board[0].add(new Info(n, Integer.parseInt(br.readLine())));
        }

        // 입력 배열에서 그냥 i==j 스킵안해도 됨
        // 왜? -> 어차피 prim에서 visited[i.dest]로 거름
        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                int cost = Integer.parseInt(st.nextToken());
                board[i].add(new Info(j, cost));
            }
        }

        bw.write(String.valueOf(prim()));
        bw.flush();
        bw.close();
        br.close();
    }
}
