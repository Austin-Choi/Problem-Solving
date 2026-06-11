import java.util.*;
import java.io.*;

/*
종료조건
M까지 가기, 머리가 격자 벗어나기, 머리가 몸통중 일부에 닿기

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.sval.charAt(0);
    }

    //udrl
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,1,-1};
    static int N,M,K;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        M = read();
        K = read();
        while(M-->0){
            int i = read()-1;
            int j = read()-1;
            board[i][j] = 1;
        }

        Map<Character, Integer> m = new HashMap<>();
        m.put('U', 0);
        m.put('D', 1);
        m.put('R', 2);
        m.put('L', 3);

        Deque<int[]> ss = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        v[0][0] = true;
        int time = 0;
        ss.addFirst(new int[]{0,0});

        outer:
        while(K-->0){
            char c = rc();
            int dir = m.get(c);
            int p = read();

            int[] cur = ss.peekFirst();
            int ci = cur[0];
            int cj = cur[1];
            
            for(int n = 1; n<=p; n++){
                int ni = ci + n*di[dir];
                int nj = cj + n*dj[dir];
                time++;
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    break outer;

                ss.addFirst(new int[]{ni,nj});
                if(board[ni][nj] == 1)
                    board[ni][nj] = 0;
                else{
                    int[] last = ss.pollLast();
                    v[last[0]][last[1]] = false;
                }

                if(v[ni][nj])
                    break outer;
                v[ni][nj] = true;
            }
        }

        System.out.print(time);
    }
}