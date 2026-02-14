import java.util.*;
import java.io.*;
public class Main {
    static int C,N,M;
    static char[][] board;

    // 6방향으로 해야함
    // -> 모든 충돌 구현
    static int[] di = {-1, 0, -1, 0,1 ,1};
    static int[] dj = {1,1, -1, -1,1,-1};
    // dest, cap, reverse
    static ArrayList<int[]>[] graph;
    static int[] lvl, work;

    static int dinic(int S, int T){
        int tot = 0;
        while(bfs(S,T)){
            Arrays.fill(work, 0);
            int flow;
            while((flow = dfs(S,T,Integer.MAX_VALUE))>0){
                tot += flow;
            }
        }
        return tot;
    }

    static boolean bfs(int S, int T){
        Arrays.fill(lvl, -1);
        Queue<Integer> q = new ArrayDeque<>();
        lvl[S] = 0;
        q.add(S);

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int[] e : graph[cur]){
                // 소스로부터 몇단계 떨어졌는지 계산
                // dfs에서 정방 용량 감소하면 여러 경로의 cap이 0이 되고(증가 경로 소진)
                // 모든 가능한 경로가 막히면 lvl[T] = -1이 되고 종료됨
                if(e[1] > 0 && lvl[e[0]] == -1){
                    lvl[e[0]] = lvl[cur] + 1;
                    q.add(e[0]);
                }
            }
        }
        return lvl[T] != -1;
    }

    static int dfs(int cur, int T, int flow){
        if(cur == T)
            return flow;

        // work : 실패 간선 다시 안보려고 체크
        for(int i = work[cur]; i < graph[cur].size(); i++, work[cur]++){
            int[] e = graph[cur].get(i);
            // cap이 0 이상이라 흘려보내기 가능하고
            // cur의 목적지가 e[0]일때
            if(e[1] > 0 && lvl[e[0]] == lvl[cur]+1){
                // 현재 경로를 따라 실제로 흘려보내는데 성공한 유량
                int ret = dfs(e[0], T, Math.min(flow, e[1]));
                // 증가 경로 하나 찾음
                if(ret > 0){
                    // 정방 용량 감소
                    e[1] -= ret;
                    // 역방 용량 증가
                    graph[e[0]].get(e[2])[1] += ret;
                    return ret;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(C-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            int dot = 0;
            for(int i = 0; i<N; i++){
                board[i] = br.readLine().toCharArray();
                for(int j = 0; j<M; j++){
                    if(board[i][j] == '.')
                        dot++;
                }
            }

            // S = 소스, T = 싱크
            int S = N*M;
            int T = N*M + 1;

            graph = new ArrayList[N*M+2];
            for(int i = 0; i<N*M+2; i++){
                graph[i] = new ArrayList<>();
            }

            lvl = new int[N*M + 2];
            work = new int[N*M + 2];

            for(int i = 0; i<N; i++){
                for(int j= 0; j<M; j++){
                    if(board[i][j] == 'x')
                        continue;
                    int cur = i*M + j;

                    if(j%2 == 0){
                        graph[S].add(new int[]{cur, 1, graph[cur].size()});
                        graph[cur].add(new int[]{S, 0, graph[S].size()-1});

                        for (int d = 0; d < 6; d++) {
                            int ni = i + di[d];
                            int nj = j + dj[d];

                            if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                            if (board[ni][nj] == 'x') continue;

                            int next = ni * M + nj;
                            graph[cur].add(new int[]{next,1,graph[next].size()});
                            graph[next].add(new int[]{cur,0,graph[cur].size()-1});
                        }
                    }
                    else{
                        graph[cur].add(new int[]{T,1,graph[T].size()});
                        graph[T].add(new int[]{cur,0,graph[cur].size()-1});
                    }

                }
            }
            sb.append(dot - dinic(S,T)).append("\n");
        }
        System.out.print(sb);
    }
}