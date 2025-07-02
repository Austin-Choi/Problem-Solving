
import java.util.*;
import java.io.*;
public class Main {
    private static int N;
    private static int M;
    private static char[][] board;
    // 0 방문 x, 1 방문중, 2 방문 완료
    private static int[][] visitState;
    private static int ans;
    private static void dfs(int i, int j){
        visitState[i][j] = 1;

        int ni = i;
        int nj = j;
        switch (board[i][j]){
            case 'U':
                ni--;
                break;
            case 'D':
                ni++;
                break;
            case 'L':
                nj--;
                break;
            case 'R':
                nj++;
                break;
        }

        if(visitState[ni][nj] == 0)
            dfs(ni,nj);
        else if(visitState[ni][nj] == 1)
            ans++;

        visitState[i][j] = 2;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visitState = new int[N][M];

        for(int i = 0; i<N; i++){
            String tmp = br.readLine();
            for(int j = 0; j<M; j++){
                board[i] = tmp.toCharArray();
            }
        }
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(visitState[i][j] == 0){
                    dfs(i,j);
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}