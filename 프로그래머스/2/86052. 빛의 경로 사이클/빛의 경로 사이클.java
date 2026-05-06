import java.util.*;

class Solution {
    // 북동남서
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    int[] dd = {0,1,2,3};
    
    int changeDir(int cd, char cmd){
        if(cmd == 'S')
            return cd;
        if(cmd == 'R')
            return (cd+1)%4;
        return (cd+3)%4;
    }
    
    //각 칸마다 북동남서 한번씩 돌려보기 
    int N,M;
    char[][] board;
    
    // 이미 어떤 사이클에 포함되는 경로인지 알아야 함
    // 만약 포함되면 이건 셀 필요없음
    int[][][] v;
    int vNum = 0;
    int bfs(int si, int sj, int sd, int cv){
        Queue<int[]> q = new ArrayDeque<>();
        v[si][sj][sd] = cv;
        q.add(new int[]{si,sj,sd,1});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            // 경로 길이
            int cl = cur[3];
            
            // 음수 모듈러 주의하기
            int ni = (ci + di[cd] +N) % N;
            int nj = (cj + dj[cd] +M) % M;
            int nd = changeDir(cd, board[ni][nj]);
            
            if(v[ni][nj][nd] == cv)
                return cl;
            // 다른 사이클
            if(v[ni][nj][nd] != 0)
                return -1;
            
            v[ni][nj][nd] = cv;
            int nl = cl+1;
            
            q.add(new int[]{ni,nj,nd,nl});
        }
        return -1;
    }
    
    public List solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        
        board = new char[N][M];
        
        for(int i = 0; i<N; i++){
            String s = grid[i];
            char[] temp = s.toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = temp[j];
            }
        }
        
        v = new int[N][M][4];
        ArrayList<Integer> ans = new ArrayList<>();
        // 방문처리를 int로 해서 같은 칸에서 시작할땐 같은 vnum을 공유하도록 해서 
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                for(int d = 0; d<4; d++){
                    // 지금 시작하려는 경로가 다른 경로에서 이미 사용되었다면 뭘 해도 그 경로 돌게 되니까
                    // 볼 필요가 없음
                    if(v[i][j][d] != 0)
                        continue;
                    // vNum 후위연산으로 시키면 0이랑 겹침
                    int a = bfs(i,j,d,++vNum);
                    if(a != -1)
                        ans.add(a);
                }
            }
        }
        
        Collections.sort(ans);
        return ans;
    }
}