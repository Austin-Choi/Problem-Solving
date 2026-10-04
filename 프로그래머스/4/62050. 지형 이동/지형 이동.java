/*
칸 최대 방문수는 약 300*300 = 90,000
병목지점 최소화 시키는 문제임 -> 모든 칸을 방문 가능하게 하면서 사다리의 비용을 최소화하는 것이므로
dfs로 모든 경우의 수 확인해보고 depth로 모든 칸 방문 가능한지 판별하면서 다 해보기??
-> 9만이라 터질거같기도 하고

다익스트라로 해보는건? 
네트워크를 구성하는 최소 비용이니까 MST
현재 방문한 영역에서 가장 적은 연결 비용으로 간선을 구성하기 -> prim
*/
import java.util.*;
class Solution {
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    int[][] board;
    int H;
    
    int prim(int si, int sj){
        int ans = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        boolean[][] v = new boolean[board.length][board.length];
        q.add(new int[]{0,0,0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cc = cur[2];
            
            // 이미 mst에 소속된거면 무시하고
            if(v[ci][cj])
                continue;
            // poll 했을때 방문처리랑 간선 비용 sum
            v[ci][cj] = true;
            ans += cc;
            
            for(int d=0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= board.length || nj >= board.length)
                    continue;
                if(v[ni][nj])
                    continue;
                
                int nc = 0;
                // 음수될수도 있음 그냥빼면
                int diff = Math.abs(board[ni][nj] - board[ci][cj]);
                if(diff > H)
                    nc = diff;   
                
                q.add(new int[]{ni,nj,nc});
            }
        }
        
        return ans;
    }
    
    public int solution(int[][] land, int height) {
        board = land;
        H = height;
        
        return prim(0,0);
    }
}