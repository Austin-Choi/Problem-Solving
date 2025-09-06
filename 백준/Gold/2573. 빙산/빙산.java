/*
di dj 동서남북
보드 입력받고 
1) 동서남북 체크해서 물 갯수 세고 copy에 업데이트하기(최솟값 0)
2) 업데이트한 보드에서 빙산 칸들 큐에 넣고 젤 처음꺼 하나 잡고 bfs돌리기
빙산 칸들 dist 모두 무한값이 아닌지 보기

다 녹았는지 먼저 체크하기 -> 0 출력
녹이고 시간 + 1 해주기
다 녹았으면 0으로 출력하고 끝내기
그냥 분리된거면 ans 출력하고 끝내기
아니면 반복하기
 */

import java.util.*;
import java.io.*;
import java.awt.Point;
public class Main {
    static int N, M;
    static int[][] board;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};

    static ArrayList<Point> ices;
    // 빙산의 아무점이나 start, ices는 빙산 모든 위치를 리스트로 넣음
    static boolean bfs(Point start){
        int[][] dist = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        dist[start.x][start.y] = 0;

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(ni < 0 || nj < 0 || ni >= N || nj >= M || board[ni][nj] == 0)
                    continue;
                if(dist[ni][nj] == Integer.MAX_VALUE) {
                    dist[ni][nj] = dist[ci][cj] + 1;
                    q.add(new Point(ni, nj));
                }
            }
        }

        for(Point p : ices){
            if(dist[p.x][p.y] == Integer.MAX_VALUE)
                return false;
        }
        return true;
    }
    static int[][] melting(){
        ices = new ArrayList<>();
        int[][] copyBoard = new int[N][M];
        for(int i = 0; i<N; i++){
            copyBoard[i] = Arrays.copyOf(board[i], M);
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] != 0){
                    int count = 0;
                    for(int d= 0; d<4; d++){
                        int ni = i + di[d];
                        int nj = j + dj[d];

                        if(ni < 0 || nj < 0 || ni >= N || nj >= M || board[ni][nj] != 0)
                            continue;
                        count++;
                    }
                    copyBoard[i][j] = Math.max(0, copyBoard[i][j] - count);
                    if(copyBoard[i][j] != 0)
                        ices.add(new Point(i,j));
                }
            }
        }
        return copyBoard;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        ices = new ArrayList<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] != 0)
                    ices.add(new Point(i,j));
            }
        }

        int ans = 0;
        while(true){
            // 다 녹음
            if(ices.isEmpty()){ 
                ans = 0;
                break;
            }
            
            board = melting(); 
            ans++;

            // 방금 다 녹음 -> 분리는 안됨
            if(ices.isEmpty()){ 
                ans = 0;
                break;
            }
            
            // 분리됨
            if(!bfs(ices.get(0)))
                break;
        }
        System.out.println(ans);
    }
}
