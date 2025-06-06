
import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX_LIMIT = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(board[i], MAX_LIMIT);
        }

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num != 0)
                    board[i][j] = num;
            }
        }

        for(int k = 0; k<N; k++){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(board[i][k] != MAX_LIMIT && board[k][j] != MAX_LIMIT)
                        board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] == MAX_LIMIT)
                    sb.append(0).append(" ");
                else
                    sb.append(1).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
