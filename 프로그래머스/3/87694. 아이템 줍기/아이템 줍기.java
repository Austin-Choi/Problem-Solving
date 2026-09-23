import java.util.*;

/*
모든 좌표 2배시키기
직사각형 경계만 true로 채우기
-> 다시 rect를 돌면서 각 직사각형의 순수 내부를 false로 지우기
-> 경계만 남음
*/

class Solution {
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    int[][] board;
    
    int bfs(int si, int sj, int ei, int ej){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[102][102];
        
        v[si][sj] = true;
        q.add(new int[]{si, sj, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            
            if(ci == ei && cj == ej){
                return cd;
            }
            
            for(int d = 0; d < 4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                
                if(ni < 0 || nj < 0 || ni >= 101 || nj >= 101)
                    continue;
                if(v[ni][nj])
                    continue;
                
                if(board[ni][nj] == 2){
                    v[ni][nj] = true;
                    q.add(new int[]{ni, nj, cd + 1});
                }
            }
        }
        
        return 0;
    }
    
    public int solution(int[][] rect, int sj, int si, int ej, int ei) {
        board = new int[102][102];
        
        //1. 모든 직사각형의 경계만 1로 채우기
        for(int[] rr : rect){
            int x1 = rr[0] * 2;
            int y1 = rr[1] * 2;
            int x2 = rr[2] * 2;
            int y2 = rr[3] * 2;
            
            // 위, 아래
            for(int x = x1; x <= x2; x++){
                board[y1][x] = 1;
                board[y2][x] = 1;
            }
            
            // 왼쪽, 오른쪽
            for(int y = y1; y <= y2; y++){
                board[y][x1] = 1;
                board[y][x2] = 1;
            }
        }
        
        // 2. 각 직사각형의 순수 내부를 0으로 지우기
        for(int[] rr : rect){
            int x1 = rr[0] * 2;
            int y1 = rr[1] * 2;
            int x2 = rr[2] * 2;
            int y2 = rr[3] * 2;
            
            for(int y = y1 + 1; y < y2; y++){
                for(int x = x1 + 1; x < x2; x++){
                    board[y][x] = 0;
                }
            }
        }
        
        // 3. 남은 경계만 2로 변경
        for(int i = 0; i < 102; i++){
            for(int j = 0; j < 102; j++){
                if(board[i][j] == 1){
                    board[i][j] = 2;
                }
            }
        }
        
        return bfs(si * 2, sj * 2, ei * 2, ej * 2) / 2;
    }
}