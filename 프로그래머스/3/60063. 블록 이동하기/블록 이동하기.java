import java.util.*;
import java.io.*;

/*
1 1  1 0  0 1  
0 0  1 0  0 1

0 0  1 0  0 1
1 1  1 0  0 1

1 0  
1 0

0 = 가로, 1 = 세로

*/

class Solution {
    //북동남서
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    
    int N;
    int[][] b;
    Set<String> visited = new HashSet<>();
    String parser(int ai, int aj, int bi, int bj){
        if(ai > bi || (ai == bi && aj > bj)){
            int ti = ai;
            int tj = aj;
            ai = bi;
            aj = bj;
            bi = ti;
            bj = tj;
        }
        return ai + "," + aj + "," + bi + "," + bj;
    }
    boolean canGo(int i, int j){
        return i >= 0 && j >= 0 && i < N && j < N && b[i][j] == 0;
    }

    int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        // ai, aj, bi, bj, dist
        q.add(new int[]{0,0,0,1,0});
        visited.add(parser(0,0,0,1));
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cai = cur[0];
            int caj = cur[1];
            int cbi = cur[2];
            int cbj = cur[3];
            int cd = cur[4];
            
            //종료 둘중하나 N,N이면
            if((cai == N-1 && caj == N-1) || (cbi == N-1 && cbj == N-1))
                return cd;
            
            // 북동남서 이동
            for(int d = 0; d<4; d++){
                int nai = cai + di[d];
                int naj = caj + dj[d];
                int nbi = cbi + di[d];
                int nbj = cbj + dj[d];
                
                if(canGo(nai, naj) && canGo(nbi, nbj)){
                    String ns = parser(nai, naj, nbi, nbj);
                    if(!visited.contains(ns)){
                        visited.add(ns);
                        q.add(new int[]{nai, naj, nbi, nbj, cd+1});
                    }
                }
            }
            
            // 피봇 하나 잡고 변화하는거만 체크하기
            // 가로 상태
            if(cai == cbi){
                //위 -1, 아래 +1
                for(int d : new int[]{-1,1}){
                    if(canGo(cai + d, caj) && canGo(cbi + d, cbj)){
                        String s1 = parser(cai, caj, cai + d, caj);
                        if(!visited.contains(s1)){
                            visited.add(s1);
                            q.add(new int[]{cai, caj, cai+d, caj, cd + 1});
                        }
                        String s2 = parser(cbi, cbj, cbi + d, cbj);
                        if(!visited.contains(s2)){
                            visited.add(s2);
                            q.add(new int[]{cbi, cbj, cbi + d, cbj, cd + 1});
                        }
                    }
                }
            }
            // 세로 상태
            else{
                // 왼쪽 -1, 오른쪽 +1
                for(int d : new int[]{-1,1}){
                    if(canGo(cai, caj + d) && canGo(cbi, cbj + d)){
                        String s1 = parser(cai, caj, cai, caj+d);
                        if(!visited.contains(s1)){
                            visited.add(s1);
                            q.add(new int[]{cai, caj, cai, caj+d,cd+1});
                        }
                        String s2 = parser(cbi, cbj, cbi, cbj+d);
                        if(!visited.contains(s2)){
                            visited.add(s2);
                            q.add(new int[]{cbi, cbj, cbi, cbj+d,cd+1});
                        }
                    }
                }
            }
        }
        return -1;
    }
    public int solution(int[][] board) {
        N = board[0].length;
        b = board;
        return bfs();
    }
}