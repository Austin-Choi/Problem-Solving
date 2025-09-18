/*
8시 - 9시
각 섬을 정점으로 보고 다리를 간선으로 보면
길이가 2 이상인 모든 다리를
다리 길이 적은 순으로 정렬해서 visited[] 체킹하면서
MST 구성해서 그 간성 총 길이 구하면 될 듯

1) 간선 구하기
한 방향으로 3~N까지 체크함
만약 ni, nj가 지금과 다른 섬이라면 글로벌 pq에 시작섬 끝섬 다리길이 넣기

2) MST 만들기
pq하나씩 꺼내면서 체킹
--------------------------------
버그 1 ) id 붙일때 첫번째꺼 꼭 붙이고 ci cj 사용
2) 동서/남북 나누지 말고 d<4 다 돌고 while(true)로 무한히 증가시키고
경계나 시작섬 만나면 죽이고 아무 다른 섬 만났을때 다리 길이 2 이상이면 ipq에 넣기
3) 사이클 없는 서로 다른 섬을 연결 할 때만 간선 채택
-> 크루스칼 사용해서 union (s,e) 성공하면 cnt++
cnt == id-2 면 다 연결된것
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[][] board;
    static int[][] idboard;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static class Info implements Comparable<Info>{
        int s;
        int e;
        int cost;
        Info(int s, int e, int c){
            this.s = s;
            this.e = e;
            this.cost = c;
        }

        @Override
        public int compareTo(Info o){
            return this.cost - o.cost;
        }
    }
    static PriorityQueue<Info> ipq = new PriorityQueue<>();

    static boolean isVaild(int i, int j){
        return (i >= 0 && j >= 0 && i < N && j < M);
    }

    static void bfs(int sid){
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(idboard[i][j] == sid){
                    q.add(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for(int d = 0; d<4; d++){
                int len = 0;
                int ni = ci;
                int nj = cj;

                // 쭉쭉 증가하면서
                // 경게를 만나거나 시작섬을 만나면 탈출
                // 다른 섬을 처음 만나면 가장 짧은 다리니까 그 전까지 len 증가시키고
                // 이때 len이 2 이상이면 ipq에 넣고 탈출
                while(true){
                    ni += di[d];
                    nj += dj[d];

                    if(!isVaild(ni, nj))
                        break;

                    if(idboard[ni][nj] == sid)
                        break;

                    if(board[ni][nj] == 1){
                        if(len >= 2)
                            ipq.add(new Info(sid, idboard[ni][nj], len));
                        break;
                    }
                    len++;
                }
            }
        }
    }
    static int ans = 0;
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b)
            return false;
        parent[b] = a;
        return true;
    }

    static boolean mst(int id){
        parent = new int[id];
        for(int i = 1; i<id; i++)
            parent[i] = i;

        int cnt = 0;
        while(!ipq.isEmpty()){
            Info cur = ipq.poll();
            if(union(cur.s, cur.e)){
                ans += cur.cost;
                cnt++;
            }
        }

        // 섬 id-1개, mst는 id-2개의 간선으로 구성
        return cnt == id-2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        idboard = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int id = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] == 1 && idboard[i][j] == 0){
                    // id부여
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});
                    idboard[i][j] = id;

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int ci = cur[0];
                        int cj = cur[1];

                        for(int d = 0; d<4; d++){
                            int ni = ci + di[d];
                            int nj = cj + dj[d];

                            if(!isVaild(ni,nj))
                                continue;
                            if(board[ni][nj] == 1 && idboard[ni][nj] == 0){
                                idboard[ni][nj] = id;
                                q.add(new int[]{ni,nj});
                            }
                        }
                    }
                    id++;
                }
            }
        }

        // 간선 만들기
        for(int sid = 1; sid < id; sid++){
            bfs(sid);
        }

        if(mst(id))
            System.out.println(ans);
        else
            System.out.println(-1);
    }
}
