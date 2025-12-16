/*
문여는걸 코스트 1 없으면 코스트 0이니까 0-1BFS Deque 써서

현재 위치에서 동서남북 탐색해서 탈옥상태이면 사용한 문 갯수 최소값으로 갱신함

0-1BFS 죄수2개 + 바깥 출발 1개 (문 사용 최소로 하는 합류지점까지 찾아야됨)
바깥과 만나야하니까 1줄 테두리에 .으로 둘러서 거기서 시작해야함
bfs에서 비용 맵을 반환해서 문일때마 3가지 맵에서 비용 다 합치면 INF로 초기화했으니
최소 값이 나올 것
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static int T,H,W;
    static char[][] board;
    static ArrayList<int[]> cs;
    static final int INF = 10001;
    static int[][] bfs(int si, int sj){
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[H][W];

        for(int i = 0; i<H; i++)
            Arrays.fill(dist[i], INF);

        dist[si][sj] = 0;
        q.addFirst(new int[]{si,sj});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int ci = cur[0];
            int cj = cur[1];

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= H || nj >= W || board[ni][nj] == '*')
                    continue;

                int cost = dist[ci][cj];
                if(board[ni][nj] == '#')
                    cost++;

                if(dist[ni][nj] > cost){
                    dist[ni][nj] = cost;
                    if(board[ni][nj] == '#')
                        q.addLast(new int[]{ni,nj});
                    else
                        q.addFirst(new int[]{ni,nj});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken())+2;
            W = Integer.parseInt(st.nextToken())+2;

            board = new char[H][W];
            for(int j = 0; j<W; j++){
                board[0][j] = '.';
                board[H-1][j] = '.';
            }
            for(int i = 0; i<H; i++){
                board[i][0] = '.';
                board[i][W-1] = '.';
            }

            cs = new ArrayList<>();
            for(int i = 1; i<H-1; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 1; j<W-1; j++){
                    board[i][j] = temp[j-1];
                    if(temp[j-1] == '$'){
                        cs.add(new int[]{i,j});
                    }
                }
            }

            int[][] d0 = bfs(0,0);
            int[][] d1 = bfs(cs.get(0)[0], cs.get(0)[1]);
            int[][] d2 = bfs(cs.get(1)[0], cs.get(1)[1]);

            int ans = INF;

            // 문 연횟수 최소로 계산된 dist를 합치고
            // 문일때는 -2해서 중복제거하고
            // dist는 INF로 초기화했으니까 최소값이 나올것
            for(int i = 0; i<H; i++){
                for(int j = 0; j<W; j++){
                    if(board[i][j] == '*')
                        continue;
                    int sum = d0[i][j] + d1[i][j] + d2[i][j];
                    if(board[i][j] == '#')
                        sum -= 2;
                    ans = Math.min(ans, sum);
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
