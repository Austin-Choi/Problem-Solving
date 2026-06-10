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
        return sst.sval.charAt(0);
    }

    static int N,M,ci,cj;
    static int[][] board;
    // 왼오위밑
    // 각 면이 바닥을 향하고 있을 때 각 방향대로 굴리면 나오는 숫자
    // static int[][] next = {{-1,-1,-1,-1},
    //             {3,4,5,2},{4,3,6,1},{6,1,5,2},{1,6,5,2},{4,3,1,6},{4,3,5,2}};
    // -> 근데 이렇게하니까 어떨땐 2의 오른쪽이 6이고 그럼..
    /*
    6면 상태 저장하는 배열 만들고 
    0  ,  1,     2,     3,      4,     5
    top, bottom, left, right, front, back?

    */
    static int[] rotate(int[] d, int dir){
        if(dir == 0){
            int t = d[1];
            d[1] = d[2];
            d[2] = d[0];
            d[0] = d[3];
            d[3] = t;
        }
        else if(dir == 1){
            int t = d[1];
            d[1] = d[3];
            d[3] = d[0];
            d[0] = d[2];
            d[2] = t;
        }
        else if(dir == 2){
            int t = d[1];
            d[1] = d[5];
            d[5] = d[0];
            d[0] = d[4];
            d[4] = t;
        }
        else{
            int t = d[1];
            d[1] = d[4];
            d[4] = d[0];
            d[0] = d[5];
            d[5] = t;
        }
        return d;
    }
    static int[] di = {0,0,-1,1};
    static int[] dj = {-1,1,0,0};

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        ci = read()-1;
        cj = read()-1;
        int[] dice = new int[]{1,6,4,3,2,5};

        board= new int[N][N];
        board[ci][cj] = dice[1];
        Map<Character, Integer> m = new HashMap<>();
        m.put('L', 0);
        m.put('R', 1);
        m.put('U', 2);
        m.put('D', 3);
        
        while(M-->0){
            char cmd = rc();
            int cd = m.get(cmd);
            int ni = ci + di[cd];
            int nj = cj + dj[cd];
            if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                continue;
            dice = rotate(dice, cd);
            board[ni][nj] = dice[1];
            ci = ni;
            cj = nj;
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                ans += board[i][j];
            }
        }
        System.out.print(ans);
    }
}