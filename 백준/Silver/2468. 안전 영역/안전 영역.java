
/*
idboard하나 만들어서 board에서 홍수보다 높은데 아직 idboard가 0인 좌표에서
 현재 id값 부여하고 bfs 시작
경계체크하고
board에서 홍수보다 높고 아직 idboard 0이면 id부여함

입력받을때 나온 가장 큰 숫자를 H로 두고 1~N 높이일때 각자 id갯수 세서 ans에다가 max 갱신
--
비 안올수도 있음
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] board;
    static int[][] idboard;
    static int id;
    static int H;
    static int[] di = {0,0,-1,1};
    static int[] dj = {-1,1,0,0};
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        H = 0;
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                H = Math.max(H,board[i][j]);
            }
        }

        for(int h = 0; h<=H; h++){
            idboard = new int[N][N];
            id = 1;
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(idboard[i][j] == 0 && board[i][j] > h){
                        idboard[i][j] = id;
                        Queue<int[]> q= new ArrayDeque<>();
                        q.add(new int[]{i,j});

                        while(!q.isEmpty()){
                            int[] cur = q.poll();
                            int ci = cur[0];
                            int cj = cur[1];

                            for(int d = 0; d<4; d++){
                                int ni = ci + di[d];
                                int nj = cj + dj[d];
                                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                                    continue;
                                if(board[ni][nj] > h && idboard[ni][nj] == 0){
                                    idboard[ni][nj] = id;
                                    q.add(new int[]{ni,nj});
                                }
                            }
                        }
                        id++;
                    }
                }
            }
            ans = Math.max(ans, id-1);
        }
        System.out.println(ans);
    }
}