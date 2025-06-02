import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX_LIMIT = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st =new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        st =new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][n+1];

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(i == j)
                    board[i][j] = 0;
                else
                    board[i][j] = MAX_LIMIT;
            }
        }

        for(int i = 0; i<m; i++){
            st =new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(board[start][end] != 0 && board[start][end] != MAX_LIMIT)
                board[start][end] = Math.min(board[start][end], cost);
            else
                board[start][end] = cost;
        }

        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(board[i][k] != MAX_LIMIT && board[k][j] != MAX_LIMIT)
                        board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        for(int i =1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(board[i][j] == MAX_LIMIT)
                    sb.append(0);
                else
                    sb.append(board[i][j]);
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
