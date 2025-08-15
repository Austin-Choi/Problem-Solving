/*
최단거리 int[][] board 출력하고
i==j 는 0 출력하고

최단 경로 복원은 누적 합치기 방식으로
플로이드 워셜 안에서 최소 거리 갱신될 때
i->k 경로와 k+j 경로를 합쳐야 함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static final int Limit = 100001;
    static int[][] board;
    static ArrayList<Integer>[][] ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        ans = new ArrayList[N+1][N+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                if(i==j)
                    board[i][j] = 0;
                else
                    board[i][j] = Limit;
                ans[i][j] = new ArrayList<>();
            }
        }

        for(int m = 0; m<M; m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(c < board[s][e]){
                board[s][e] = c;
            }
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(board[i][k] != Limit && board[k][j] != Limit){
                        if(board[i][j] > board[i][k]+board[k][j]){
                            board[i][j] = board[i][k]+board[k][j];
                            ans[i][j] = new ArrayList<>(ans[i][k]);
                            ans[i][j].add(k);
                            for(int n : ans[k][j]){
                                ans[i][j].add(n);
                            }
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                sb.append(board[i][j] >= Limit ? 0 : board[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j || board[i][j] >= Limit){
                    sb.append("0").append(" ");
                }
                else{
                    sb.append(ans[i][j].size()+2).append(" ");
                    sb.append(i).append(" ");
                    for(int n : ans[i][j]){
                        sb.append(n).append(" ");
                    }
                    sb.append(j);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

}
