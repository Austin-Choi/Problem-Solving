import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    // 상하좌우 방향 순서대로 우선순위가 있으니까 
    // 우좌하상 방향대로 돌고 max 갱신하면 자동으로 만족?
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static ArrayList<Integer> li = new ArrayList<>();

    static void move(int ci, int cj){
        int max = 0;
        int mi = -1;
        int mj = -1;
        boolean found = false;
        for(int d = 0; d<4; d++){
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                continue;
            if(board[ni][nj] > board[ci][cj]){
                found = true;
                mi = ni;
                mj = nj;
            }
        }
        if(!found)
            return;
        li.add(board[mi][mj]);
        move(mi,mj);
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int n : li){
            sb.append(n).append(" ");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        int si = read()-1;
        int sj = read()-1;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }
        li.add(board[si][sj]);
        move(si,sj);
        print();
    }
}