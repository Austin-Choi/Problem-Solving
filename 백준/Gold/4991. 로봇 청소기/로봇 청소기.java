/*
더러운칸과 더러운칸 사이는 항상 고정이고
더러운 칸에서 더러운 칸으로 가는게 최단 경로의 종류임
그러면 미리 더러운 칸끼리 경로를 계산해서 그래프로 저장해놓고
시작 칸과 더러운 칸의 경로만 그래프로 추가하고
-> bfs로 찾기
모든 칸을 방문하는 최단 경로를 찾으면 되지 않을까..

근데 중복을 허용하고 10개를 넘지 않으니까 비트마스크로 표현하면 어떨까?
그 상태에 도달하는 최소값을 갱신하는거임
dist[mask][현재 위치]
 */
import java.util.*;
import java.io.*;
public class Main {
    static int w, h;
    static char[][] board;
    static int[] di = {1,-1,0,0};
    static int[] dj = {0,0,1,-1};
    static ArrayList<int[]> vertices;
    static int[][] g;
    static final int INF = 21*21;
    static void makeGraph(int si, int sj){
        int sv = board[si][sj] - '0';
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj,0});
        boolean[][] visited = new boolean[h][w];
        visited[si][sj] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= h || nj >= w)
                    continue;
                if(board[ni][nj] == 'x')
                    continue;
                if(!visited[ni][nj]){
                    visited[ni][nj] = true;
                    q.add(new int[]{ni,nj,cd+1});
                    int isv = board[ni][nj] - '0';
                    if(isv >= 0 && isv <= 10){
                        g[sv][isv] = Math.min(g[sv][isv], cd+1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0)
                break;
            board = new char[h][w];
            vertices = new ArrayList<>();
            int dirt = 0;
            for(int i =0; i<h; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j<w; j++){
                    if(temp[j] == 'o') {
                        board[i][j] = '0';
                        vertices.add(new int[]{i,j});
                    }
                    else if(temp[j] == '*') {
                        board[i][j] = (char) ('1' + dirt++);
                        vertices.add(new int[]{i,j});
                    }
                    else
                        board[i][j] = temp[j];
                }
            }

            // 비트dp 할거니까 배열로 받고
            // 최소값 저장이니까 최대값으로 g를 설정함
            g = new int[dirt+1][dirt+1];
            for(int i = 0; i<=dirt; i++){
                Arrays.fill(g[i], INF);
            }
            for(int[] v : vertices){
                makeGraph(v[0], v[1]);
            }

            // 못 잇는 정점 있으면 다음 테케로 넘어감
            boolean fail = false;
            for(int i = 0; i<=dirt; i++){
                for(int j = 0; j<=dirt; j++){
                    if(i!=j && g[i][j] == INF){
                        fail = true;
                    }
                }
            }
            if(fail){
                System.out.println(-1);
                continue;
            }

            // 비트 dp
            int[][] dp = new int[1<<dirt][dirt+1];
            for(int i = 0; i<(1<<dirt); i++)
                Arrays.fill(dp[i], INF);

            // i번 정점에 도달하기 전에 기본값은 출발점에서 i에 도달하는 cost
            // start는 0번이고 dirt가 1번인데
            // bit는 0번부터 시작해서 i-1, j-1 주의해서 구현
            for(int i = 1; i<=dirt; i++){
                dp[1<<(i-1)][i] = g[0][i];
            }

            for(int m = 0; m<(1<<dirt); m++){
                // 현재 더러운 칸
                for(int i = 1; i<=dirt; i++){
                    // i번 방문하지 않았으면
                    // 해당 상태는 dp[m][i]가 의미가 없으므로 넘겨야함
                    if((m & (1<<(i-1))) == 0)
                        continue;
                    if(dp[m][i] == INF)
                        continue;

                    // 다음 더러운 칸
                    for(int j = 1; j<=dirt; j++){
                        // 이미 j를 방문했으면 넘김
                        if((m & (1<<(j-1))) != 0)
                            continue;
                        int next = m | (1<<(j-1));
                        dp[next][j] = Math.min(dp[next][j], dp[m][i] + g[i][j]);
                    }
                }
            }

            int complete = (1<<dirt) - 1;
            int ans = INF;
            for(int i =1; i<=dirt; i++)
                ans = Math.min(ans, dp[complete][i]);
            System.out.println(ans);
        }
    }
}
