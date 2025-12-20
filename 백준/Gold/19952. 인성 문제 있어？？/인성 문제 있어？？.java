/*
int dist[i][j] = F이하의 힘을 이용해서 i,j에 도달했을때의 최대 남은 힘
높이차이로 발생하는 점프 값은 cost가 아님!!!!!!!!!!!!
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T,H,W,O,F;
    // 1-based
    static int si,sj,ei,ej;
    static int[][] board;

    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};

    static int[][] dist;
    static void bfs(){
        dist = new int[H][W];
        for(int i = 0; i<H; i++){
            Arrays.fill(dist[i], -1);
        }
        dist[si][sj] = F;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->b[2]-a[2]);
        // i,j,curF
        q.add(new int[]{si,sj,F});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cf = cur[2];
            int cl = board[ci][cj];

            if(cf < dist[ci][cj]) continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= H || nj >= W)
                    continue;

                int nl = board[ni][nj];

                int nc = 1;

                if(cf >= (nl-cl)){
                    int newF = cf - nc;
                    if(newF > dist[ni][nj]){
                        dist[ni][nj] = newF;
                        q.add(new int[]{ni,nj,newF});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken())-1;
            sj = Integer.parseInt(st.nextToken())-1;
            ei = Integer.parseInt(st.nextToken())-1;
            ej = Integer.parseInt(st.nextToken())-1;

            board = new int[H][W];
            while(O-->0){
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken())-1;
                int j = Integer.parseInt(st.nextToken())-1;
                board[i][j] = Integer.parseInt(st.nextToken());
            }

            bfs();
            if(dist[ei][ej] >= 0)
                sb.append("잘했어!!").append("\n");
            else
                sb.append("인성 문제있어??").append("\n");
        }
        System.out.println(sb);
    }
}
