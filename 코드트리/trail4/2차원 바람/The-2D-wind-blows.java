import java.util.*;
import java.io.*;

/*
경계 숫자들을 inplace로 할수 있나..

평균 구하기는 동시에 일어남
-> copy 빈 배열 만들어놓고 
-> 내부에 속하는 i,j 의 동서남북 board[ni][nj] 4칸중에 valid 할때만 cnt++, sum+= 하고 sum/cnt
-> 이거 copy i,j에 저장
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,M, Q;
    static int[][] board;

    static void sol(int si, int sj, int ei, int ej){
        rotate(si,sj,ei,ej);
        setAvg(si,sj,ei,ej);
    }

    static void rotate(int si, int sj, int ei, int ej){
        //list에 쭉 저장하고 알맞게 넣기?
        // inplce 연습
        int prev = board[si+1][sj];
        for(int j = sj; j<=ej; j++){
            int t = board[si][j];
            board[si][j] = prev;
            prev = t;
        }

        for(int i = si+1; i<=ei; i++){
            int t = board[i][ej];
            board[i][ej] = prev;
            prev = t;
        }

        for(int j = ej-1; j>=sj; j--){
            int t = board[ei][j];
            board[ei][j] = prev;
            prev = t;
        }

        for(int i = ei-1; i>=si; i--){
            int t = board[i][sj];
            board[i][sj] = prev;
            prev = t;
        }

    }

    static void setAvg(int si, int sj, int ei, int ej){
        int[][] copy = new int[N][M];
        //이거안하면 바깥부분 0됨.
        for(int i = 0; i<N; i++){
            for(int j= 0; j<M; j++){
                copy[i][j] = board[i][j];
            }
        }

        for(int i = si; i<= ei; i++){
            for(int j = sj; j<=ej; j++){
                int sum = board[i][j];
                int cnt = 1;
                for(int d = 0; d<4; d++){
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                        continue;
                    cnt++;
                    sum+=board[ni][nj];
                }
                copy[i][j] = sum/cnt;
            }
        }
        board = copy;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        Q = read();

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
            }
        }

        while(Q-->0){
            int r1 = read()-1;
            int c1 = read()-1;
            int r2 = read()-1;
            int c2 = read()-1;
            sol(r1,c1,r2,c2);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}