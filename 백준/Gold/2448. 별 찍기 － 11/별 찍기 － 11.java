
import java.util.*;
import java.io.*;

public class Main {
    private static StringBuilder sb;
    private static char[][] board;
    private static void star(int i, int j, int N){
        if(N == 3){
            board[i][j] = '*';
            board[i+1][j-1] = '*';
            board[i+1][j+1] = '*';
            for(int n = j-2; n<=j+2; n++){
                board[i+2][n] = '*';
            }
        }
        else{
            star(i, j, N/2);
            star(i+N/2, j-N/2, N/2);
            star(i+N/2, j+N/2, N/2);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        board = new char[n][n*2];
        for(int i = 0; i<n; i++){
            Arrays.fill(board[i], ' ');
        }

        star(0, n-1, n);

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n*2; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
