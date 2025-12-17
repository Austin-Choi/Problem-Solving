/*
N개 정점을 모두 연결하는 mst구하는데
cost가 낮은 방향으로 하는데
cost가 같다면 조정에서 정해준 도로종류 우선순위 따라감
-> 프림 pq에서 람다식으로 쓰기
프림으로 mst 뽑아서
최종 mst 비용합, 0,1,2 종류 순으로 갯수랑 비용합 따로 기록해놓고 출력
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    // 우선순위 저장하는거
    // 도로종류 넣으면 우선순위 값 나옴(적은게 먼저임)
    static HashMap<Integer, Integer> valToIdx;
    static class Info{
        int dest;
        int cost;
        int type;
        Info(int d, int c, int t){
            this.dest =d;
            this.cost =c;
            this.type =t;
        }
    }
    static ArrayList<Info>[] board;

    // 시작 1, 끝 N
    static void prim(){
        PriorityQueue<Info> q = new PriorityQueue<>((a,b)->{
            if(a.cost != b.cost)
                return a.cost-b.cost;
            return valToIdx.get(a.type) - valToIdx.get(b.type);
        });
        for(Info n : board[1]){
            q.add(n);
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        int[] roadCount = new int[3];
        int[] roadCost = new int[3];
        int chosen = 0;

        while(!q.isEmpty() && chosen < N-1){
            Info cur = q.poll();
            int cd = cur.dest;
            int cc = cur.cost;
            int ct = cur.type;

            if(visited[cd])
                continue;

            visited[cd] = true;
            chosen++;

            roadCount[ct]++;
            roadCost[ct] += cc;

            for(Info n : board[cd]){
                if(!visited[n.dest]){
                    q.add(n);
                }
            }
        }

        int tot = 0;
        for(int tc : roadCost){
            tot += tc;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tot).append("\n");
        for(int i = 0; i<3; i++){
            sb.append(roadCount[i]).append(" ")
                    .append(roadCost[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        valToIdx = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<3; i++){
            int val = Integer.parseInt(st.nextToken());
            valToIdx.put(val, i);
        }

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            board[u].add(new Info(v,c,t));
            board[v].add(new Info(u,c,t));
        }

        prim();
    }
}
