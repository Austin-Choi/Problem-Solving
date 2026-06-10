import java.util.*;
import java.io.*;

/*
북동남서 0123
해당 방향으로 오른쪽 -> dir + 1

dir로 이동한 ni,nj가 1일때 -> nd = (dir-1+4) % 4
move(ci,cj,nd)

dir로 이동한 ni,nj가 1이 아닐때
1) ni,nj가 invalid 하면 탈출

2) ni, nj,가 valid 한데 right = (dir+1)%4 해서 right에 있는 board값이 1이면 
move(ni, nj,dir)

3) ni, nj,가 valid 한데 right해서 board값이 0이면
nd = (right+1)%4해서 
ni = ni + di[nd] , nj 해서 
move(ni, nj, nd)
*/

public class Main {
    static int N, si, sj;
    static int[][] board;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static boolean[][][] v;
    static int ans = 0;
    

    // 같은곳 계속 도는거 처리 필요함
    static void move(int ci, int cj, int d, int len){
        if(v[ci][cj][d]){
            ans = -1;
            return;
        }
        v[ci][cj][d] = true;

        int ni = ci + di[d];
        int nj = cj + dj[d];
        if(ni < 0 || nj < 0 || ni >= N || nj >= N){
            ans = len+1;
            return;
        }
        if(board[ni][nj] == 1){
            int nd = (d-1+4)%4;
            move(ci,cj,nd,len);
        }
        else{
            int right = (d+1)%4;
            int ri = ni + di[right];
            int rj = nj + dj[right];
            
            if(board[ri][rj] == 0){
                move(ri, rj, right, len+2);
            }
            else{
                move(ni, nj, d, len + 1);
            }
        }
    }

    static int ssi, ssj;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        si = Integer.parseInt(st.nextToken())-1;
        sj = Integer.parseInt(st.nextToken())-1;
        ssi = si;
        ssj = sj;
        board = new int[N][N];

        for(int i= 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                if(t[j] == '#')
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
        v = new boolean[N][N][4];
        move(si, sj, 1, 0);
        System.out.print(ans);
    }
}