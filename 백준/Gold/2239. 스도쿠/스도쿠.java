
/*
숫자가 중복되지 않게 1~9에서 순열을 뽑기
-> dfs,이용한 백트래킹

빈칸 기준 사전순으로 뽑고
가로도 봐야하지만 세로도 봐야함..
-> isAvailable로 가로세로 체크해서 현재 숫자 가능한지 보기

 */

import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {
    private static final int N = 9;
    private static int[][] board = new int[N][N];
    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<Point> toFills = new ArrayList<>();
    private static boolean filled = false;
    // board의 빈칸을 쭉 늘어놓고 하나의 큰 list로 해서 순열 구성하기
    private static void dfs(int idx){
        if(filled)
            return;

        if(idx == toFills.size()){
            filled = true;
            return;
        }

        int cI = toFills.get(idx).x;
        int cJ = toFills.get(idx).y;

        for(int i = 1; i<=N; i++){
            if(isAvailable(cI, cJ, i)){
                board[cI][cJ] = i;
                dfs(idx + 1);
                // 정답이 완성됬으면 백트래킹안함
                if(filled)
                    return;
                board[cI][cJ] = 0;
            }
        }
    }
    // 빈칸에 num을 넣는게 가능한지
    private static boolean isAvailable(int i, int j, int num){
        for(int n = 0; n<N; n++){
            if(board[i][n] == num) {
                return false;
            }
        }

        for (int n = 0; n<N; n++){
            if(board[n][j] == num){
                return false;
            }
        }

        // 3*3 box
        int si = (i/3)*3;
        int sj = (j/3)*3;
        for(int n = si; n< si+3; n++){
            for(int m = sj; m< sj+3; m++){
                if(board[n][m] == num)
                    return false;
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i<N; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                board[i][j] = tmp[j] - '0';
                if(board[i][j] == 0)
                    toFills.add(new Point(i,j));
            }
        }

        // board를 받아와서 그걸 채워나가는 dfs
        // 탈출 조건은 현재 board[i]에 빈 칸 갯수랑 같아지면
        // board를 완성하고 StringBuilder sb에 append
        dfs(0);

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
