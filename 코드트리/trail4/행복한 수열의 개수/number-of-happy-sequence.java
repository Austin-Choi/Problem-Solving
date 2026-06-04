import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        if(M == 1){
            System.out.print(2*N);
            return;
        }
        board = new int[N][N];
        for(int i =0 ; i<N; i++){
            for(int j =0; j<N; j++){
                board[i][j] = read();
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            int j = 1;
            int prev = board[i][0];
            int cnt = 1;
            while(j < N){
                if(prev == board[i][j]){
                    cnt++;
                    if(cnt == M){
                        ans++;
                        break;
                    }
                }
                else{
                    cnt = 1;
                }
                prev = board[i][j];
                j++;
            }
        }

        for(int j = 0; j<N; j++){
            int i = 1;
            int prev = board[0][j];
            int cnt = 1;
            while(i < N){
                if(prev == board[i][j]){
                    cnt++;
                    if(cnt == M){
                        ans++;
                        break;
                    }
                }
                else{
                    cnt = 1;
                }
                prev = board[i][j];
                i++;
            }
        }
        System.out.print(ans);
    }
}