/*
moves는 1-based
[0,5][move]에서 가장 위에 있는 0이 아닌것 칸 기록해두기
호출할때마다 1칸씩 줄어들고 줄어들기만 하니까
-> topIdx

0 0 0 0 0
0 0 1 0 3
0 2 5 0 1
4 2 4 4 2
3 5 1 3 1

1
// 4
0 0 0 0 0
0 0 1 0 3
0 2 5 0 1
0 2 4 4 2
3 5 1 3 1

5
// 3 4
0 0 0 0 0
0 0 1 0 0
0 2 5 0 1
0 2 4 4 2
3 5 1 3 1

3 
// 1 3 4
0 0 0 0 0
0 0 0 0 0
0 2 5 0 1
0 2 4 4 2
3 5 1 3 1

5
ans 2
// 3 4
0 0 0 0 0
0 0 0 0 0
0 2 5 0 1
0 2 4 4 2
3 5 1 3 1

1
ans 4
// 4
0 0 0 0 0
0 0 0 0 0
0 2 5 0 1
0 2 4 4 2
0 5 1 3 1

2
ans 4
// 2 4
0 0 0 0 0
0 0 0 0 0
0 0 5 0 1
0 2 4 4 2
0 5 1 3 1

1
ans 4
// 2 4
0 0 0 0 0
0 0 0 0 0
0 0 5 0 1
0 2 4 4 2
0 5 1 3 1

4
ans 4
// 2 4
0 0 0 0 0
0 0 0 0 0
0 0 5 0 1
0 2 4 0 2
0 5 1 3 1
*/
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int ans = 0;
        int N = board.length;
        
        // j번 열에서 0이 아닌 가장 위에있는 칸의 idx값
        // N-1이면 바닥에 도달한것 (해당 열은 빈 것)
        int[] topIdx = new int[N];
        for(int j = 0; j<N; j++){
            for(int i = 0; i<N; i++){
                if(board[i][j] != 0){
                    topIdx[j] = i;
                    break;
                }
            }
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int m : moves){
            m -= 1;
            if(topIdx[m] >= N)
                continue;
            
            // 크레인으로 집은거 cur
            int cur = board[topIdx[m]][m]; 
            board[topIdx[m]][m] = 0;
            topIdx[m]++;
            
            if(!dq.isEmpty()){
                int top = dq.peekFirst();
                if(top == cur){
                    ans += 2;
                    dq.pollFirst();
                }
                else
                    dq.addFirst(cur);
            }
            else{
                dq.addFirst(cur);
            }
        }
        
        return ans;
    }
}