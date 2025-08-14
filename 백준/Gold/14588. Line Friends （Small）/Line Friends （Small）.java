/*
20분
선분 -> 정점
가중치 -> 1 (선분간 연결 여부)

겹침 정의
as, ae, bs, be 받아서
Math.max(as,bs) <= Math.min(ae,be)
 */
import java.util.*;
import java.io.*;
public class Main {

    static boolean isFriend(int as, int ae, int bs, int be){
        return Math.max(as,bs) <= Math.min(ae,be);
    }
    static int N, Q;
    static final int ML = 300 * 300 + 1;
    static int[][] board;
    static ArrayList<int[]> queries;
    static ArrayList<int[]> lines;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        lines = new ArrayList<>();
        lines.add(new int[]{-1,-1});
        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines.add(new int[]{s,e});
        }

        Q = Integer.parseInt(br.readLine());
        queries = new ArrayList<>();
        for(int i = 0; i<Q; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            queries.add(new int[]{s,e});
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    board[i][j] = 0;
                else
                    board[i][j] = ML;
            }
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    continue;
                else{
                    if(isFriend(lines.get(i)[0], lines.get(i)[1], lines.get(j)[0], lines.get(j)[1]))
                        board[i][j] = 1;
                }
            }
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(board[i][k] != ML && board[k][j] != ML)
                        board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        for(int[] q : queries){
            int dist = board[q[0]][q[1]];
            if(dist != ML)
                bw.write(String.valueOf(dist));
            else
                bw.write("-1");
            bw.write("\n");
        }
        bw.flush();
    }
}
