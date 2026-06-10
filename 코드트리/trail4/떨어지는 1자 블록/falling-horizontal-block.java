import java.util.*;
import java.io.*;

/*
board랑 H
H에는 행방향으로 열을 봤을때 가장 멀리 있는 0의 위치임 없으면 -1
[K, K+M-1] 구간에 대해 최솟값을 구하고 이를 si라 하면
board[si]에 대해 [K, K+M-1]을 1로 채우고 
H[K, K+M-1]을 전부 si로 업데이트함
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // K는 1-based로 들어옴
    static int N,M,K;
    static int[][] board;
    static int[] H;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read()-1;
        
        board = new int[N][N];
        // H[j] = j열에 블록이 떨어졌을때 위치할 최대 위치
        // H N-1로 꼭 초기화해야함
        H = new int[N];
        Arrays.fill(H, N-1);

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }    

        for(int j = 0; j<N; j++){
            for(int i = 0; i<N; i++){
                if(board[i][j] != 0){
                    H[j] = i-1;
                    break;
                }
            }
        }

        int min = N-1;
        for(int j = K; j<=K+M-1; j++){
            min = Math.min(min, H[j]);
        }

        for(int j = K; j<=K+M-1; j++){
            H[j] = min;
            board[min][j] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}