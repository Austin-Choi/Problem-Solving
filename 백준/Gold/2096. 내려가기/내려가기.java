import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N= Integer.parseInt(br.readLine());
        int[][] board = new int[N][3];
        int[][] mindp = new int[N][3];
        int[][] maxdp = new int[N][3];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mindp[0] = board[0];
        maxdp[0] = board[0];
        for(int i = 1; i<N; i++){
            mindp[i][0] = board[i][0] + Math.min(mindp[i-1][0], mindp[i-1][1]);
            mindp[i][1] = board[i][1] + Math.min(Math.min(mindp[i-1][0], mindp[i-1][1]), mindp[i-1][2]);
            mindp[i][2] = board[i][2] + Math.min(mindp[i-1][2], mindp[i-1][1]);

            maxdp[i][0] = board[i][0] + Math.max(maxdp[i-1][0], maxdp[i-1][1]);
            maxdp[i][1] = board[i][1] + Math.max(Math.max(maxdp[i-1][0], maxdp[i-1][1]), maxdp[i-1][2]);
            maxdp[i][2] = board[i][2] + Math.max(maxdp[i-1][2], maxdp[i-1][1]);
        }
        int max = Math.max(Math.max(maxdp[N-1][0], maxdp[N-1][1]), maxdp[N-1][2]);
        int min = Math.min(Math.min(mindp[N-1][0], mindp[N-1][1]), mindp[N-1][2]);

        sb.append(max).append(" ").append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
