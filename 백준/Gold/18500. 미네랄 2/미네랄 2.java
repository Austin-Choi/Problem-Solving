
/*
1) 시작 방향에서 가장 가까운 X 찾아내서 보드에서 지움
2) 파괴됬을때 좌표 기준으로 왼오위 X인 곳에서 BFS 시작해서
맨아래 아무칸이나 닿을 수 있으면 그냥 놔둠
3) 닿을 수 없으면 bfs에서 dist가 1 이상인 모든 칸 중에서 파괴된 칸과 같은 y좌표를 가진 모든 좌표를 바닥 칸으로 잡고
이 dist가 1 이상인 모든 좌표를 Y좌표 아래쪽으로 한칸 내리고
 바닥칸이 X를 만나거나 바닥을 만나면 종료
 -> 복잡한디
--------------------------------------------
바닥 기준 BFS로 grounded로 표시
floating 집합을 구성하고 floating을 빈칸으로 간주해서 최소 낙하거리 계산한 뒤
-> floats 집함은 
한꺼번에 내림
 */
import java.awt.*;
import java.util.*;
import java.io.*;
public class Main {
    static int R,C,N;
    static char[][] board;
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    static int[][] dist;
    static boolean[][] visited;
    static final int DMAX = 10002;
    static int[] shoot(int si, boolean isLeft){
        int sj = 0;
        if(!isLeft){
            sj = C-1;
            for(int j = sj; j>=0; j--){
                if(board[si][j] == 'x'){
                    board[si][j] = '.';
                    return new int[]{si,j};
                }
            }
        }else{
            for(int j = sj; j<C; j++){
                if(board[si][j] == 'x'){
                    board[si][j] = '.';
                    return new int[]{si,j};
                }
            }
        }

        return new int[] {-1,-1};
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for(int i = 0; i<R; i++){
            board[i] = br.readLine().toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean isLeft = true;
        for(int n = 0; n<N; n++){
            int h = Integer.parseInt(st.nextToken());
            int si = R - h;
            int[] shootRst = shoot(si, isLeft);
            isLeft = !isLeft;

            if(shootRst[0] == -1)
                continue;

            // 아래서부터 BFS 수행하기
            // touchdown 이 true면 땅에 붙은거임
            boolean[][] touchdown = new boolean[R][C];
            Queue<Point> q = new ArrayDeque<>();
            for(int j = 0; j<C; j++){
                if(board[R-1][j] == 'x'){
                    touchdown[R-1][j] = true;
                    q.add(new Point(R-1, j));
                }
            }
            while(!q.isEmpty()){
                Point p = q.poll();
                for(int d = 0; d<4; d++){
                    int ni = p.x + di[d];
                    int nj = p.y + dj[d];
                    if(ni < 0 || nj < 0 || ni >= R || nj >= C)
                        continue;
                    if(!touchdown[ni][nj] && board[ni][nj] == 'x'){
                        touchdown[ni][nj] = true;
                        q.add(new Point(ni,nj));
                    }
                }
            }

            // 떠있는 클러스터 floats 구성하기
            ArrayList<Point> floats = new ArrayList<>();
            boolean[][] isFloating = new boolean[R][C];
            for(int i = 0; i<R; i++){
                for(int j = 0; j<C; j++){
                    if(board[i][j] == 'x' && !touchdown[i][j]){
                        floats.add(new Point(i,j));
                        isFloating[i][j] = true;
                    }
                }
            }

            // 떠있는거 없음
            if(floats.isEmpty())
                continue;

            // 최소 drop 거리 계산하기
            int drop = 101;
            for(Point p : floats){
                int i = p.x;
                int j = p.y;
                int k = i+1;
                while(k<R && (board[k][j] == '.' || isFloating[k][j]))
                    k++;
                int cand = k - i - 1;
                drop = Math.min(drop, cand);
            }

            // 내리기
            for(Point p : floats)
                board[p.x][p.y] = '.';
            for(Point p : floats)
                board[p.x+drop][p.y] = 'x';
        }

        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
