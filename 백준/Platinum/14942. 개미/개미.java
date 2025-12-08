/*
개미들 각 줄번호가 있는 방 번호임
1-based 정점번호로 들어옴

다익으로 1번에서 n번방까지 최소거리 dist를 지표로
..
현재 트리라서 최단경로가 유일하기때문에 dist 필요없음
그냥 bfs로 parent랑 parent로 가는 cost 갱신

이진점프에서는 현재 정점 v의 2^k 조상이 누군지(없으면-1)
그 점프에 드는 비용 배열 채워넣기

이진 점프 전처리해서 제일 큰 점프부터 개미 에너지에서 할수 있으면 하고
재귀적으로 해서 최종 room 번호 출력하게끔
(0-base라 +1)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    // 몇번째 방에 몇 에너지를 갖고있는지
    static long[] ants;
    static class Info{
        int dest;
        long cost;
        Info(int d, long c){
            this.dest = d;
            this.cost = c;
        }
    }
    static ArrayList<Info>[] board;
    static final long INF = 10_000L * 100_000 + 1;

    static int[] parent;
    static long[] parentCost;

    static int LOG;
    // up kv = v의 2^k 조상
    static int[][] up;
    // costup kv = 정점 v에서 2^k 조상까지 가는 비용합
    static long[][] costup;
    static void dijkstra(){
        parent = new int[N];
        Arrays.fill(parent, -1);
        parentCost = new long[N];
        Arrays.fill(parentCost, 0L);

        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        parent[0] = -1;
        parentCost[0] = 0;

        while(!q.isEmpty()){
            int cd = q.poll();

            for(Info n : board[cd]){
                int nd = n.dest;
                // 중요!! 부모로 되돌아가는 간선은 건너뛰어야함
                if(parent[cd] == nd)
                    continue;
                parent[nd] = cd;
                parentCost[nd] = n.cost;
                q.add(nd);
            }
        }
    }

    static void binaryLifting(){
        LOG = 1;
        while((1<<LOG)<=N)
            LOG++;

        up = new int[LOG][N];
        costup = new long[LOG][N];

        // 한단계 위
        for(int v = 0; v<N; v++){
            up[0][v] = parent[v];
            if(parent[v] == -1)
                costup[0][v] = INF;
            else
                costup[0][v] = parentCost[v];
        }

        // 2단계부터
        for(int k = 1; k<LOG; k++){
            for(int v = 0; v<N; v++){
                int mid = up[k-1][v];

                // v의 2^k-1조상 없으면 점프안됨
                if(mid==-1){
                    up[k][v] = -1;
                    costup[k][v] = INF;
                }
                else{
                    // mid 조상도 있어야됨
                    int midup = up[k-1][mid];
                    if(midup == -1){
                        up[k][v] = -1;
                        costup[k][v] = INF;
                    }
                    else{
                        up[k][v] = midup;
                        long a = costup[k-1][v];
                        long b = costup[k-1][mid];
                        if(a == INF || b == INF)
                            costup[k][v] = INF;
                        else{
                            if(a+b >= INF)
                                costup[k][v] = INF;
                            else
                                costup[k][v] = a+b;
                        }
                    }
                }
            }
        }
    }

    // 현재 room에서 energy로 올라갈수 있는 가장 위의 노드 반복문으로
    // 큰점프 먼저해서 점프 비용만큼 에너지에서 빼고 
    // 선택 없으면 현재 room이 최종임
    static int rc(int room, long curEnergy){
        for(int k = LOG-1; k>=0; k--){
            if(up[k][room] != -1 && costup[k][room] <= curEnergy) {
                curEnergy -= costup[k][room];
                room = up[k][room];
            }
        }
        return room;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ants = new long[N];
        for(int i = 0; i<N; i++){
            ants[i] = Long.parseLong(br.readLine());
        }
        board = new ArrayList[N];
        for(int i = 0; i<N; i++){
            board[i] = new ArrayList<>();
        }
        for(int i = 0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            long c = Long.parseLong(st.nextToken());
            board[s].add(new Info(e,c));
            board[e].add(new Info(s,c));
        }

        // 최단경로 트리 구하기
        dijkstra();
        // binarylifting 전처리
        binaryLifting();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            int ans = rc(i, ants[i]);
            sb.append(ans+1).append("\n");
        }
        System.out.println(sb);
    }
}
