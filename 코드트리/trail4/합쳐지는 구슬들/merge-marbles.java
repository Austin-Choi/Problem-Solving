import java.util.*;
import java.io.*;


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
    
    static int N,M,T;
    static int[] ctoi = new int[128];
    // 번호, i,j,dir,weight
    static Map<Integer, int[]> ball = new HashMap<>();
    //북동남서
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    //1초 이동
    static void move(){
        // <cn, cd, cw> [i][j]
        ArrayList<int[]>[][] board = new ArrayList[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(int cn : ball.keySet()){
            int[] cur = ball.get(cn);
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            int cw = cur[3];

            int ni = ci + di[cd];
            int nj = cj + dj[cd];
            int nd = cd;

            // 반대로 방향만 뒤집기
            if(ni < 0 || nj < 0 || ni >= N || nj >= N){
                ni = ci;
                nj = cj;
                nd = (cd+2)%4;
            }
            
            board[ni][nj].add(new int[]{cn, nd, cw});
        }

        ball = new HashMap<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j].isEmpty())
                    continue;
                int mn = 0;
                int md = -1;
                int wSum = 0;

                for(int[] it : board[i][j]){
                    int cn = it[0];
                    int cd = it[1];
                    int cw = it[2];

                    if(cn > mn){
                        mn = cn;
                        md = cd;
                    }
                    wSum += cw;
                }

                ball.put(mn, new int[]{i,j,md,wSum});
            }
        }
    }

    public static void main(String[] args) throws IOException{
        ctoi['U'] = 0;
        ctoi['R'] = 1;
        ctoi['D'] = 2;
        ctoi['L'] = 3;

        N = read();
        M = read();
        T = read();
        for(int m=1; m<=M; m++){
            ball.put(m, new int[]{read()-1,read()-1,ctoi[rc()],read()});
        }

        for(int t = 0; t<T; t++){
            move();
        }

        int cnt = ball.size();
        int max = 0;
        for(int cn : ball.keySet()){
            int[] b = ball.get(cn);
            max = Math.max(max, b[3]);
        }
        System.out.print(cnt+" "+max);
    }
}