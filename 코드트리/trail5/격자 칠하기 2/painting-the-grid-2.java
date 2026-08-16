import java.util.*;
import java.io.*;

/*
임의로 시작칸을 잘 정하기 
-> 모든칸 다해보거나 최적 시작칸 찾거나 

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, D, T;
    static int[][] board;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    static boolean can(int X){
        boolean[][] v =new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(v[i][j])
                    continue;
                
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i,j});
                v[i][j] = true;
                int cnt = 0;

                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int ci = cur[0];
                    int cj = cur[1];
                    cnt++;

                    if(cnt == T)
                        return true;
                
                    for(int d = 0; d<4; d++){
                        int ni = ci + di[d];
                        int nj = cj + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        if(v[ni][nj])
                            continue;
                        if(Math.abs(board[ni][nj] - board[ci][cj]) <= X){
                            v[ni][nj] = true;
                            q.add(new int[]{ni,nj});
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        T = (N*N % 2 == 0) ? N*N/2 : N*N/2+1;
        int min = 1_000_001;
        int max= 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }

        int l = 0;
        int r = max- min;
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        System.out.print(ans);
    }
}