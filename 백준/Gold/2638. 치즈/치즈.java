
import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] dist;
    private static int[] di = {-1,1,0,0};
    private static int[] dj = {0,0,-1,1};

    // 0,0에서 시작해서 모든 outer air까지의 dist 구하기
    private static void checkingOuter(){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0));

        while(!q.isEmpty()){
            Point cur = q.poll();
            int cI = cur.x;
            int cJ = cur.y;

            for(int k = 0; k<4; k++){
                int nI = cI+di[k];
                int nJ = cJ+dj[k];
                if(nI > -1 && nJ > -1 && nI < N && nJ < M){
                    if(dist[nI][nJ] == 0 && board[nI][nJ] == 0){
                        dist[nI][nJ] = dist[cI][cJ] + 1;
                        q.add(new Point(nI, nJ));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        //답구하는 반복문

        while(true){
            dist = new int[N][M];
            boolean isAllGone = true;
            int[][] copy = new int[N][M];
            for(int i = 0; i<N; i++){
                copy[i] = Arrays.copyOf(board[i], M);
            }

            checkingOuter();

            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(board[i][j] == 1){
                        isAllGone = false;
                        int outerCount = 0;
                        for(int k = 0; k<4; k++){
                            int nI = i+di[k];
                            int nJ = j+dj[k];
                            if(nI > -1 && nJ > -1 && nI < N && nJ < M && outerCount < 2){
                                if(dist[nI][nJ] != 0)
                                    outerCount++;
                            }
                        }
                        if(outerCount >= 2){
                            copy[i][j] = 0;
                        }
                    }
                }
            }
            if(isAllGone)
                break;
            else {
                ans++;
                for(int i = 0; i<N; i++){
                    board[i] = Arrays.copyOf(copy[i], M);
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
