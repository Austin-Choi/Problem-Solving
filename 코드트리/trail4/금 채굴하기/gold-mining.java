import java.util.*;
import java.io.*;

/*
마름모 모양 채굴해야함 
하드코딩은 좀 그렇고 K값 받아서 마름모로 탐색하는 함수 짜야함

북동남서 K칸 대각선 K-1칸 bfs로 하면 다음 level에서 제한 1씩 줄어듬 
-> 복잡한데
        -2,2
    -1,1
0,0 0,1 0,2 0,3 0,4

abs(i-ci) + abs(j-cj) <= K 이면 마름모 안에 들어감
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[][] board;
    static int cost;

    static int calc(int si, int sj, int K){
        int sum = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(Math.abs(i-si)+Math.abs(j-sj) <= K)
                    sum += board[i][j];
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][N];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        int ans = 0;
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int k = 0; k<=N; k++){
                    int cost = k*k + (k+1)*(k+1);
                    int cur = calc(i,j,k);
                    if(cur*M >= cost){
                        ans = Math.max(ans, cur);
                    }
                }
            }
        }
        System.out.print(ans);
    }
}