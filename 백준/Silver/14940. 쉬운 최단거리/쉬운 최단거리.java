
import java.util.*;
import java.io.*;

/*
4 4
0 1 0 0
1 0 0 0
0 0 0 0

일때 갈수 없는 땅이 있어서

0 1 -1 -1
1 -1 -1 -1
-1 -1 -1 -1

로 출력해야함
 */
class Info{
    public int i;
    public int j;
    Info(int i , int j){
        this.i = i;
        this.j = j;
    }
}

public class Main {
    private static int n;
    private static int m;
    private static boolean[][] visited;

    private static int[][] board;

    // 거리를 나타내는 거리 배열 보드
    private static int[][] dist;

    private static int[][] ans;
    private static int[] di = {1,0,-1,0};
    private static int[] dj = {0, 1, 0, -1};
    private static void bfs(Info coord){
        Queue<Info> q = new ArrayDeque<>();
        q.add(coord);
        visited[coord.i][coord.j] = true;

        while(!q.isEmpty()){
            Info curCoord = q.poll();
            int curI = curCoord.i;
            int curJ = curCoord.j;

            for(int i = 0; i<4; i++){
                int nextI = curI+di[i];
                int nextJ = curJ+dj[i];

                if(nextI > -1 && nextJ > -1
                        && nextI < n && nextJ < m
                        && board[nextI][nextJ] != 0){
                    if(!visited[nextI][nextJ]){
                        visited[nextI][nextJ] = true;
                        dist[nextI][nextJ] = dist[curI][curJ] + 1;
                        q.add(new Info(nextI, nextJ));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        dist = new int[n][m];
        for(int i = 0; i<n; i++){
            Arrays.fill(dist[i], -1);
        }
        Info startCoord = new Info(0,0);


        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 2){
                    startCoord = new Info(i,j);
                    board[i][j] = 1;//지나갈 수 있는 땅으로 간주함.
                    dist[i][j] = 0;
                }
                else
                    board[i][j] = num;
            }
        }

        bfs(startCoord);

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j] == 0)
                    sb.append(0);
                else if (!visited[i][j])
                    sb.append(-1);
                else
                    sb.append(dist[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
