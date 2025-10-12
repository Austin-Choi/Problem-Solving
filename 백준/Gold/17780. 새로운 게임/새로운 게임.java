import java.util.*;
import java.io.*;
public class Main {
    static int[][] color;
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    static class Info{
        int i;
        int j;
        int dir;
        Info(int i, int j, int dir){
            this.i = i;
            this.j = j;
            this.dir = dir;
        }
    }
    static List<Integer>[][] board;
    static Info[] infos;
    static int N,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N+1][N+1];
        board = new ArrayList[N+1][N+1];
        infos = new Info[K+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }

        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;

            infos[i] = new Info(r, c, d);
            board[r][c].add(i);
        }

        System.out.println(simulation());
    }

    public static int simulation() {
        int turn = 0;

        while(turn <= 1000) {
            turn++;

            for(int k = 1; k <= K; k++) {
                Info info = infos[k];
                int i = info.i;
                int j = info.j;

                if(board[i][j].get(0) != k) continue;

                int ni = i + di[info.dir];
                int nj = j + dj[info.dir];

                if(ni < 1 || ni > N || nj < 1 || nj > N || color[ni][nj] == 2) {
                    info.dir = changeDirection(info.dir);
                    ni = i + di[info.dir];
                    nj = j + dj[info.dir];

                    if(ni < 1 || ni > N || nj < 1 || nj > N || color[ni][nj] == 2) {
                        continue;
                    }
                }

                if(color[ni][nj] == 0) {
                    moveWhite(i, j, ni, nj);
                } else if(color[ni][nj] == 1) {
                    moveRed(i, j, ni, nj);
                }

                if(board[ni][nj].size() >= 4) {
                    return turn;
                }
            }
        }

        return -1;
    }

    public static int changeDirection(int dir) {
        if(dir == 0) return 1;
        if(dir == 1) return 0;
        if(dir == 2) return 3;
        return 2;
    }

    public static void moveWhite(int i, int j, int ni, int nj) {
        List<Integer> current = board[i][j];
        List<Integer> next = board[ni][nj];

        for(int idx = 0; idx < current.size(); idx++) {
            int horse = current.get(idx);
            infos[horse].i = ni;
            infos[horse].j = nj;
            next.add(horse);
        }

        current.clear();
    }

    public static void moveRed(int i, int j, int ni, int nj) {
        List<Integer> current = board[i][j];
        List<Integer> next = board[ni][nj];

        for(int idx = current.size() - 1; idx >= 0; idx--) {
            int horse = current.get(idx);
            infos[horse].i = ni;
            infos[horse].j = nj;
            next.add(horse);
        }

        current.clear();
    }
}