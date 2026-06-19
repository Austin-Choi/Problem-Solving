import java.util.*;
import java.io.*;

/*
일단 id랑 idBoard로 현재 방문값이 아니면 그걸로 매겨주는 방식으로 && board == 1
idBoard 채워주고 현재 id값과 같은 값을 가진 지점을 모두 시작지점으로 넣고 
bfs해서 자기보다 큰 id값을 가지는 땅을 만났을 때 g에 입력하는 multisource-BFS 수행
그리고 그 g를 가지고 prim 수행
prim 수행한 뒤 visited 전부 검사해서 하나라도 false면 sum 리턴 대신 -1 리턴하기
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int id = 1;
    static int[][] idBoard;
    static int[][] board;
    static ArrayList<int[]>[] g;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int[][] cost;

    static long prim(){
        boolean[] v = new boolean[K];
        int[] dist = new int[K];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        long sum = 0;
        for(int t = 1; t<K; t++){
            int cur = -1;
            for(int i =1 ;i<K; i++){
                if(v[i])
                    continue;
                if(cur == -1 || dist[i] <dist[cur])
                    cur = i;
            }

            if(cur == -1 || dist[cur] == Integer.MAX_VALUE)
                return -1;
            v[cur] = true;
            sum += dist[cur];

            for(int n = 1; n<K; n++){
                if(v[n])
                    continue;
                if(cost[cur][n] < dist[n]){
                    dist[n] = cost[cur][n];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        idBoard = new int[N][M];
        for(int i = 0; i<N; i++)
            Arrays.fill(idBoard[i], -1);

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
            }
        }

        // 1) id 라벨링
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(idBoard[i][j] == -1 && board[i][j] == 1){
                    idBoard[i][j] = id;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int ci = cur[0];
                        int cj = cur[1];

                        for(int d = 0; d<4; d++){
                            int ni = ci + di[d];
                            int nj = cj + dj[d];
                            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                                continue;
                            if(board[ni][nj] == 1 && idBoard[ni][nj] == -1){
                                idBoard[ni][nj] = id;
                                q.add(new int[]{ni,nj});
                            }
                        }
                    }
                    id++;
                }
            }
        }

        K = id;
        g = new ArrayList[K];
        for(int i = 1; i<K; i++)
            g[i] = new ArrayList<>();

        // 각 ID 땅마다 시작 지점 모아놓는 리스트배열
        ArrayList<int[]>[] ss = new ArrayList[K];
        for(int i = 1; i<K; i++)
            ss[i] = new ArrayList<>();

        
        for(int i = 0; i<N; i++){
            for(int j= 0; j<M; j++){
                if(idBoard[i][j] == -1)
                    continue;
                ss[idBoard[i][j]].add(new int[]{i,j});
            }
        }

        // 각 땅으로 이어지는 간선 가중치 구하기
        // 1->2에 여러 값 들어갈 수 있음.
        // 직선만 가능해서 bfs하면 안됨
        // g에는 i->j min 값만 저장하기
        cost = new int[K][K];
        for(int i= 1; i<K; i++){
            for(int j = 1; j<K; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i=1; i<K; i++){
            for(int[] s : ss[i]){
                int ci = s[0];
                int cj = s[1];
                // 직선으로 찾음
                for(int d = 0;d<4; d++){
                    int l = 1;
                    while(true){
                        int ni = ci + l*di[d];
                        int nj = cj + l*dj[d];
                        // 범위 밖 나가면 멈춰야함
                        if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                            break;
                        // 자기자신 만나도 멈추고
                        if(idBoard[ni][nj] == i)
                            break;
                        // 다른땅 만나도 갱신하고 멈춰야함
                        if(board[ni][nj] == 1 && idBoard[ni][nj] != i){
                            int nid = idBoard[ni][nj];
                            cost[i][nid] = Math.min(cost[i][nid], l-1);
                            cost[nid][i] = cost[i][nid];
                            break;
                        }
                        l++;
                    }
                }
            }
        }

        System.out.print(prim());
    }
}