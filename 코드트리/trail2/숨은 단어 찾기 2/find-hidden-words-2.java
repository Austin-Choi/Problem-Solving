/*
이건 8방향 다봐야함 아까 오목은 방향성이 없었는데 EEL, LEE 이런거 다봐야함
*/
import java.util.*;
import java.io.*;

public class Main {
    // 시계방향
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static int N,M;
    static char[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j =0; j<M; j++){
                board[i][j] = t[j];
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0;  j<M; j++){
                if(board[i][j] != 'L')
                    continue;
                
                for(int d = 0; d<8; d++){
                    int ni = i;
                    int nj = j;
                    int cnt = 0;
                    while(cnt < 2){
                        ni = ni + di[d];
                        nj = nj + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                            break;
                        if(board[ni][nj] !='E')
                            break;
                        cnt++;
                    }
                    if(cnt == 2)
                        ans++;
                }
            }
        }
        System.out.print(ans);
    }
}