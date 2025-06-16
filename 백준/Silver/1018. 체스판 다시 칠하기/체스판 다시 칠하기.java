
import java.util.*;
import java.io.*;

public class Main {
    private static char[][] bwBoard;
    private static char[][] wbBoard;
    private static final String bwS = "BWBWBWBW";
    private static final String wbS = "WBWBWBWB";
    private static int countDiffBw(char[] board){
        int sum = 0;
        char[] bw = bwS.toCharArray();
        for(int i = 0; i < board.length; i++){
            if(bw[i] != board[i])
                sum++;
        }
        return sum;
    }
    private static int countDiffWb(char[] board){
        int sum = 0;
        char[] wb = wbS.toCharArray();
        for(int i = 0; i < board.length; i++){
            if(wb[i] != board[i])
                sum++;
        }
        return sum;
    }

    private static int countDiff(char[][] board){
        int bwsum = 0;
        int wbsum = 0;
        for(int i = 0; i<8; i++){
            if(i % 2 == 0){
                bwsum += countDiffBw(board[i]);
                wbsum += countDiffWb(board[i]);
            }
            else{
                bwsum += countDiffWb(board[i]);
                wbsum += countDiffBw(board[i]);
            }
        }
        return Math.min(bwsum, wbsum);
    }

    private static char[][] copyBoard(char[][] board, int I, int J){
        char[][] rst = new char[8][8];
        for(int i = 0; i<8; i++){
            rst[i] = Arrays.copyOfRange(board[i+I], J, J+8);
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];

        for(int n = 0; n<N; n++){
            board[n] = br.readLine().toCharArray();
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i<=N-8; i++){
            for(int j = 0; j<=M-8; j++){
                 char[][] cur = copyBoard(board, i,j);
                 int diff = countDiff(cur);
                 if(diff < min)
                     min = diff;
            }
        }

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}
