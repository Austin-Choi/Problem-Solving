import java.util.*;
/*
얼마나 많이 채울수 있는지 알기
모양에 딱 맞아야함 -> 회전은 가능
1) 보드에서 무슨 구멍이 존재하는지 알기 (연속된 0의 모양)
2) 테이블에서 무슨 조각이 존재하는지 알기 (연속된 1의 모양)
-> 회전이 가능하다는데 어떻게 회전한 것과 안 회전한 것이 같다는걸 표현하지
-> 조각 비교할때마다 bfs하는건 부적절한거같음
-> 0이든 1이든 연속된 값을 갖는 좌표들을 저장하고 i,j 좌표 각각의 최솟값을 구해서 빼주면 정규화됨
3) 2번에서 구한 조각들을 적절히 회전해서 보드에서의 구멍을 완전히 채우는 것이 있는지 확인하고
있으면 그 조각 크기만큼 ans에 더하기
*/
class Solution {
    int[] di = {0,0,1,-1};
    int[] dj = {1,-1,0,0};
    
    // 90도 회전결과
    ArrayList<int[]> rotate(ArrayList<int[]> p){
        ArrayList<int[]> rst =new ArrayList<>();
        int minI = Integer.MAX_VALUE;
        int minJ = Integer.MAX_VALUE;
        for(int[] pp : p){
            int i = pp[0];
            int j = pp[1];
            
            rst.add(new int[]{j, -i});
            minI = Math.min(minI, j);
            minJ = Math.min(minJ, -i);
        }
        
        
        for(int[] pp : rst){
            pp[0] -= minI;
            pp[1] -= minJ;
        }
        
        Collections.sort(rst, (a,b)->{
            if(a[0] != b[0])
                return a[0]-b[0];
            return a[1]-b[1];
        });
        
        return rst;
    }
    
    public int solution(int[][] board, int[][] table) {
        int N = board.length;
        // 1
        boolean[][] v1 = new boolean[N][N];
        ArrayList<ArrayList<int[]>> bPieces = new ArrayList<>();
        
        for(int si = 0; si<N; si++){
            for(int sj = 0; sj<N; sj++){
                if(board[si][sj] == 1)
                    continue;
                if(v1[si][sj])
                    continue;
                
                int minI = si;
                int minJ = sj;
                ArrayList<int[]> curPiece = new ArrayList<>();
                Queue<int[]> q = new ArrayDeque<>();
                v1[si][sj] = true;
                q.add(new int[]{si,sj});
                curPiece.add(new int[]{si,sj});
                
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int ci = cur[0];
                    int cj = cur[1];
                    
                    for(int d = 0; d<4; d++){
                        int ni = ci + di[d];
                        int nj = cj + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        if(v1[ni][nj])
                            continue;
                        if(board[ni][nj] == 1)
                            continue;
                        v1[ni][nj] = true;
                        q.add(new int[]{ni,nj});
                        minI = Math.min(minI, ni);
                        minJ = Math.min(minJ, nj);
                        curPiece.add(new int[]{ni,nj});
                    }
                }
                
                for(int[] pp : curPiece){
                    pp[0] -= minI;
                    pp[1] -= minJ;
                }
                
                bPieces.add(curPiece);
            }
        }
        
        // 2
        boolean[][] v2 = new boolean[N][N];
        ArrayList<ArrayList<int[]>> tPieces = new ArrayList<>();
        
        for(int si = 0; si<N; si++){
            for(int sj = 0; sj<N; sj++){
                if(table[si][sj] == 0)
                    continue;
                if(v2[si][sj])
                    continue;
                
                int minI = si;
                int minJ = sj;
                ArrayList<int[]> curPiece = new ArrayList<>();
                Queue<int[]> q = new ArrayDeque<>();
                v2[si][sj] = true;
                q.add(new int[]{si,sj});
                curPiece.add(new int[]{si,sj});
                
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int ci = cur[0];
                    int cj = cur[1];
                    
                    for(int d = 0; d<4; d++){
                        int ni = ci + di[d];
                        int nj = cj + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        if(v2[ni][nj])
                            continue;
                        if(table[ni][nj] == 0)
                            continue;
                        v2[ni][nj] = true;
                        q.add(new int[]{ni,nj});
                        minI = Math.min(minI, ni);
                        minJ = Math.min(minJ, nj);
                        curPiece.add(new int[]{ni,nj});
                    }
                }
                
                for(int[] pp : curPiece){
                    pp[0] -= minI;
                    pp[1] -= minJ;
                }
                
                tPieces.add(curPiece);
            }
        }
        
        // bPieces랑 tPieces 모양 비교할때 좌표 순서 같게 해야하고
        // 4방향 회전하고 서로 모양 비교해서 일치하는 거 있으면 크기세서 ans에 더해주기
        for(ArrayList<int[]> p : bPieces){
            Collections.sort(p, (a,b)->{
                if(a[0] != b[0]){
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            });
        }
        for(ArrayList<int[]> p : tPieces){
            Collections.sort(p, (a,b)->{
                if(a[0] != b[0]){
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            });
        }
        
        int ans = 0;
        boolean[] v = new boolean[tPieces.size()];
        for(ArrayList<int[]> curB : bPieces){
            ArrayList<int[]> rtB = curB;
            
            outer:
            for(int d = 0; d<4; d++){
                for(int idx = 0; idx < tPieces.size(); idx++){
                    if(v[idx])
                        continue;
                    ArrayList<int[]> curT = tPieces.get(idx);
                    int curN = curT.size();
                    if(rtB.size() != curN)
                        continue;
                    boolean all = true;
                    for(int i = 0; i<curN; i++){
                        int[] rtBp = rtB.get(i);
                        int[] curTp = curT.get(i);
                        if(rtBp[0] != curTp[0] || rtBp[1] != curTp[1]){
                            all = false;
                            break;
                        }
                    }
                    if(all){
                        ans += curN;
                        v[idx] = true;
                        break outer;
                    }
                }
                
                rtB = rotate(rtB);
            }
        }
        return ans;
    }
}