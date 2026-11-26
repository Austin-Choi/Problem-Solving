import java.io.*;
import java.util.*;
/*
* 미로는 8*8고정이므로 8초동안 살아남을수 있으면 무조건 true임
* 이동할때는 빈칸으로만 하고 만약 벽이 이동했는데 캐릭터랑 겹치면 false
* -> 현재 벽, 다음 벽 위치는 사용 불가능
* -> 어차피 64개니까 벽 위치 저장해서 할까
* -> N-1,0에서 출발해서 0,N-1로 가기
* 
* 레벨 단위 격자 그래프 bfs
* */

public class Main {
    static int N = 8;
    static char[][] board = new char[N][N];
    // 8방향 북쪽부터 시계방향
    // 제자리도 허용해서 9가지
    static int[] di = {-1,-1,0,1,1,1,0,-1,0};
    static int[] dj = {0,1,1,1,0,-1,-1,-1,0};

    static boolean bfs(int si, int sj){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj,0});

        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-- > 0){
                int[] cur = q.poll();
                int ci = cur[0];
                int cj = cur[1];
                int cd = cur[2];

                if(board[ci][cj] == '#')
                    continue;
                if(cd > 7)
                    return true;
                if(ci == 0 && cj == N-1)
                    return true;

                for(int d = 0; d<9; d++){
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                        continue;
                    if(board[ni][nj] == '#')
                        continue;
                    q.add(new int[]{ni,nj,cd+1});
                }
            }
            // 아래서부터 위로 해야 함
            // 위애서 아래로 하면 계속 내려감
            for(int i = N-1; i>=1; i--){
                for(int j = 0; j<N; j++){
                    board[i][j] = board[i-1][j];
                }
            }
            Arrays.fill(board[0], '.');
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<N; j++) {
                board[i][j] = t[j];
            }
        }
        if(board[N-1][0] == '#') {
            System.out.print(0);
            return;
        }

        if(bfs(N-1, 0))
            System.out.print(1);
        else
            System.out.print(0);
    }
}
