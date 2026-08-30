import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,C;
    static int[][] board;
    static int ans = 0;

    // r,c로 시작하는 M개의 칸에서
    // 무게 C 이하로 훔칠수 있는 최대 value 구하기
    static int getVal(int r, int c){
        int rst =0;
        for(int mask = 0; mask < (1<<M); mask++){
            int weight = 0;
            int sum = 0;
            for(int k = 0; k < M; k++){
                if((mask & (1<<k)) != 0){
                    int w = board[r][c+k];
                    weight += w;
                    sum += w*w;
                }
            }

            if(weight <= C)
                rst = Math.max(rst, sum);
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        C = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        int[][] maxVal = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<=N-M; j++){
                maxVal[i][j] = getVal(i,j);
            }
        }

        for(int i1 = 0; i1<N; i1++){
            for(int j1 = 0; j1<= N-M; j1++){

                for(int i2 = 0; i2<N; i2++){
                    for(int j2 = 0; j2<= N-M; j2++){
                        // 같은 행인데 겹치는 경우
                        if(i1 == i2){
                            if(j1 + M > j2 && j2 + M > j1)
                                continue;
                        }
                        ans = Math.max(ans, maxVal[i1][j1] + maxVal[i2][j2]);
                    }
                }
            }
        }

        System.out.print(ans);
    }
}