

import java.util.*;
import java.io.*;

/*
bfs로 탐색해서 P 몇개 찾는지 카운팅
 */
class Info{
    private final int posI;
    private final int posJ;
    private final char curVal;
    public Info(int posI, int posJ, char curVal){
        this.posI = posI;
        this.posJ = posJ;
        this.curVal = curVal;
    }

    public int getPosI(){
        return this.posI;
    }
    public int getPosJ(){
        return this.posJ;
    }
    public char getCurVal(){
        return this.curVal;
    }
}

public class Main {
    private static int boardH = 0;
    private static int boardL = 0;
    //  동남서북
    private static final int[] di ={1,0, -1, 0};
    private static final int[] dj ={0, 1, 0, -1};

    //P갯수
    private static int ans = 0;
    private static char[][] board;

    //bfs로 찾기
    // 큐 만들어서
    // 일단 현재 값과 위치를 둘 다 큐에 넣고
    // 큐가 빌때까지 첫번째꺼부터 하나씩 빼면서
    // 조건 맞춰서 visited 체크해가면서 탐색하고

    private static Queue<Info> q = new ArrayDeque<>();
    private static void bfs(int i, int j, boolean[][] visited){
        q.add(new Info(i, j, board[i][j]));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Info temp = q.poll();
            int curI = temp.getPosI();
            int curJ = temp.getPosJ();
            char curVal = temp.getCurVal();

            for(int n = 0; n<4; n++){
                int nextI = curI+di[n];
                int nextJ = curJ+dj[n];
                if(nextI > -1 && nextJ > -1
                        && nextI < boardH && nextJ < boardL
                        && !(board[nextI][nextJ] == 'X')){
                    if(!visited[nextI][nextJ]) {
                        if(board[nextI][nextJ] == 'P'){
                            ans += 1;
                        }
                        q.add(new Info(nextI, nextJ, board[nextI][nextJ]));
                        visited[nextI][nextJ] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        boardH = Integer.parseInt(st.nextToken());
        boardL = Integer.parseInt(st.nextToken());
        int startI = 0;
        int startJ = 0;
        board = new char[boardH][boardL];
        boolean[][] visited = new boolean[boardH][boardL];

        for(int n = 0; n<boardH; n++){
            char[] cl = br.readLine().toCharArray();
            for(int m = 0; m<boardL; m++){
                board[n][m] = cl[m];
                if(board[n][m] == 'I'){
                    startI = n;
                    startJ = m;
                }
            }
        }

        bfs(startI, startJ, visited);
        if(ans == 0)
            sb.append("TT");
        else
            sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
