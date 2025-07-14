import java.util.*;
import java.io.*;
class State{
    int ri;
    int rj;
    int bi;
    int bj;
    int depth;

    public State(int ri, int rj, int bi, int bj, int depth){
        this.ri = ri;
        this.rj = rj;
        this.bi = bi;
        this.bj = bj;
        this.depth = depth;
    }
}
public class Main {
    static char[][] board;
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    // 동서남북
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static boolean[][][][] visited;

    static class MoveResult {
        int ni, nj;
        // 옮긴 횟수, 나중에 겹쳤을때 더 적게 옮겨진 게
        // 그 방향으로 앞에 가있던 거라
        // 더 많이 옮겨진 애를 그 방향이 커지는 크기에서 -1 해줘야함
        int count;
        boolean isHole;

        MoveResult(int ni, int nj, int count, boolean isHole){
            this.ni = ni;
            this.nj = nj;
            this.count = count;
            this.isHole = isHole;
        }
    }
    static MoveResult move(int ci, int cj, int dir){
        int ni = ci, nj = cj;
        int cnt = 0;
        while(true){
            int nexti = ni + di[dir];
            int nextj = nj + dj[dir];

            if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M)
                break;

            if(board[nexti][nextj] == '#')
                break;
            ni = nexti;
            nj = nextj;
            cnt++;

            if(board[ni][nj] == 'O')
                return new MoveResult(ni, nj, cnt, true);
        }
        return new MoveResult(ni, nj, cnt, false);
    }
    static void bfs(int sri, int srj, int sbi, int sbj, int depth){
        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(sri, srj, sbi, sbj, depth));
        visited[sri][srj][sbi][sbj] = true;

        while(!q.isEmpty()){
            State cur = q.poll();
            int cri = cur.ri;
            int crj = cur.rj;
            int cbi = cur.bi;
            int cbj = cur.bj;

            if(cur.depth > 10){
                sb.append(-1);
                return;
            }

            for(int i = 0; i<4; i++){
                MoveResult red = move(cri, crj, i);
                MoveResult blue = move(cbi, cbj, i);

                // 파란공이 빠지면 실패
                if(blue.isHole)
                    continue;

                // 파란공이 안빠지고 빨간 공만 빠지면 성공
                if(red.isHole){
                    if(cur.depth == 10){
                        sb.append(-1);
                        return;
                    }
                    sb.append(cur.depth+1);
                    return;
                }

                // 겹치는 경우 -> 더 많이 이동한 쪽을 한칸 뒤로 옮기기
                if(red.ni == blue.ni && red.nj == blue.nj){
                    if(red.count > blue.count){
                        red.ni -= di[i];
                        red.nj -= dj[i];
                    }
                    else{
                        blue.ni -= di[i];
                        blue.nj -= dj[i];
                    }
                }

                if(!visited[red.ni][red.nj][blue.ni][blue.nj]){
                    visited[red.ni][red.nj][blue.ni][blue.nj] = true;
                    q.offer(new State(red.ni, red.nj, blue.ni, blue.nj, cur.depth+1));
                }
            }
        }

        sb.append(-1);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int sri = 0, srj = 0, sbi = 0, sbj = 0;
        for(int i = 0; i<N; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                char c = tmp[j];
                board[i][j] = c;
                if(c == 'R'){
                    sri = i;
                    srj = j;
                }
                else if(c == 'B'){
                    sbi = i;
                    sbj = j;
                }
            }
        }

        // bfs
        bfs(sri, srj, sbi, sbj, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
