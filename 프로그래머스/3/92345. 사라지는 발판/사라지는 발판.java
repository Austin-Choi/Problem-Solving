import java.util.*;
/*
이동할때 1) 보드밖 안되고 2) 발판이어야 하고 3) 한번 플레이어가 떠났던 발판(플레이어 종류 상관없이)도 비활성화 되므로 안됨
A는 이동할때 B에 최대한 인접한 칸을 고름 
-> 맨해튼 거리로 측정해서 제일 가까운 칸
B는 A에서 최대한 떨어진 칸을 고르는데 오래 버텨야함
-> A가 존재하는 방향의 반대방향이 1번째 고려, A가 존재하는 가까운쪽 방향이 마지막 고려, 나머지 두 방향은 우선순위 2번째로 둘다 같음
-> 3가지 (A가 존재하지 않는 방향 2가지는 우선순위 같으므로) 중 이동할때 조건 맞춰서 고르기
보드 크기가 커봐야 5*5라 그냥 시뮬레이션해도 괜찮을 듯 
초기좌표값 0-based
--------------------
-> 맨해튼 거리로 그리디하게 풀면 미래 최적값이 보장안됨
-> 그냥 다봐야함

ans 제거하고 int[] 반환형 [0] = 현재 플레이어가 이기는지, [1] = 거리
a,b 상태로 나누지 말고 
현재 플레이어를 depth로 판단하고 현재 플레이어 기준으로 backtracking하기
-> bt 넘어간 다음 상태로 판단하면 (자식 상태)
다음 상태에서 이기면 현재 플레이어가 지는거니까 최대한 버티기로 선택
다음 상태에서 지면 현재 플레이어가 이기는거니까 최소한 빨리 이기는 수 (depth 작은거로) 선택
*/
class Solution {
    // 북동남서
    int[] di = {-1, 0, 1, 0};
    int[] dj = {0, 1, 0, -1};
    
    // aStart, bStart 0-based
    int getDist(int[] a, int[] b){
        return Math.abs(a[1]-b[1]) + Math.abs(a[0] - b[0]);
    }

    boolean equalPos(int[] a, int[] b){
        return a[0] == b[0] && a[1] == b[1];
    }
    
    // 현재 depth에서의 board 상태, cur a pos, cur b pos, depth count, 세로, 가로
    int[] bt(int[][] board, int[] a, int[] b, int depth, int N, int M){
        int ci;
        int cj;
        
        if(depth % 2 == 0){
            ci = a[0];
            cj = a[1];
            
            if(board[ci][cj] == 0){
                return new int[]{0, 0};
            }
        }
        else{
            ci = b[0];
            cj = b[1];
            
            if(board[ci][cj] == 0)
                return new int[]{0, 0};
        }
        

        boolean hasMove = false;
        // 현재 플레이어가 이기는 선택이 있는지
        boolean win = false;
        
        // 이기는 경우 가장 짧은 횟수
        int minWin = Integer.MAX_VALUE;
        // 지는 경우 가장 긴 횟수
        int maxLose = 0;

        for(int d =0; d<4; d++){
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                continue;
            if(board[ni][nj] == 0)
                continue;

            hasMove = true;
            board[ci][cj] = 0;
            int[] rst;
            
            if(depth % 2 ==0){
                rst = bt(board, new int[]{ni,nj},b,depth+1,N,M);
            }
            else{
                rst = bt(board, a, new int[]{ni,nj},depth+1,N,M);
            }
            board[ci][cj] = 1;
            
            int count = rst[1] + 1;
            // rst는 그다음 수 상태니까
            // 상대가 승리하면
            if(rst[0] == 1){
                maxLose = Math.max(maxLose, count);
            }
            // 현재 플레이어가 이김
            else{
                win = true;
                minWin = Math.min(minWin, count);
            }
        }

        // 이동 못함
        if(!hasMove){
            return new int[]{0,0};
        }
        
        // 이길수 있는 방법이 있으면 가장 빨리 이기는쪽으로 선택
        if(win)
            return new int[]{1, minWin};
        // 이길수 있는 방법이 없음
        return new int[]{0, maxLose};
    }
    
    public int solution(int[][] board, int[] as, int[] bs) {
        // 세로
        int N = board.length;
        // 가로
        int M = board[0].length;
        int[] best = bt(board, as, bs, 0, N, M);
        return best[1];
    }    
}
    
    
