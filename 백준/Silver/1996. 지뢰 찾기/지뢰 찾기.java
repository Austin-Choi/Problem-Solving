import java.io.*;
public class Main {
    static int[] di = {-1,-1,-1,0,1,1,1,0};
    static int[] dj = {-1,0,1,1,1,0,-1,-1};
    static int N;
    static char[][] board;
    static int[][] bombs;
    static void print(int si, int sj, int bomb){
        board[si][sj] = '*';
        for(int d=0; d<8; d++){
            int ni = si + di[d];
            int nj = sj + dj[d];
            if(ni < 0 || nj <0 || ni >=N || nj >= N)
                continue;
            if(board[ni][nj] != '.')
                continue;
            bombs[ni][nj] += bomb;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        bombs = new int[N][N];

        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                board[i][j] = temp[j];
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] != '.')
                    print(i,j,board[i][j] - '0');
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] != '*'){
                    if(bombs[i][j] < 10){
                        board[i][j] = (char)(bombs[i][j] + '0');
                    }
                    else
                        board[i][j] = 'M';
                }
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}