import java.util.*;
import java.io.*;

/*
10
11

11
10

11
01

01
11

111

1
1
1

2*2칸 잡고 저거 모양대로 4개 
왼->오 3칸
위->아래 3칸
-> 총 6칸 구현
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // 6 3 2 -> 왼쪽위 시작지점 기준 저거대로 더하다가 세개중에 ni,nj가 invalid 하면 continue
    // 세개 다 valid 하면 적힌 숫자 합 ans에 maximize
    static int[][][] block = {{{0,0},{1,0},{1,1}},{{0,0},{0,1},{1,0}},{{0,0},{0,1},{1,1}},{{0,1},{1,0},{1,1}}
                                ,{{0,0},{0,1},{0,2}},{{0,0},{1,0},{2,0}}};
    static int[][] board;
    static int N,M;
    static int ans = -1;

    static void calc(int si, int sj){
        outer:
        for(int i =0 ; i<6; i++){
            int sum = 0;
            for(int k = 0; k<3; k++){
                int ni = si + block[i][k][0];
                int nj = sj + block[i][k][1];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue outer;
                sum += board[ni][nj];
            }
            ans = Math.max(ans, sum);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][M];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                calc(i,j);
            }
        }
        System.out.print(ans);
    }
}