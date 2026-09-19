import java.util.*;
/*
1 5 6 10이고 전체 길이가 12면 5~6(1), 10~1(3) 1,3 2명으로 커버
9~10~13~15~16 -> 7 하나로 커버 가능
1+3+2+1 = 7

1 5 6 10 13 17 18 22 
-> weak를 원래 weak + n을 offset으로 더한 weak로 2배 늘리고
연속으로 weak의 갯수만큼 커버할수 있는지 보기?
5~6 10~13

1 3 4 9 10 13 15 16 21 22

1) sliding window로 n-w 갯수만큼 확인하고 window 크기는 w(weak n)
2) dist 순열로 모든 순열 검사해서 어떤 순서로 친구를 배치해야 cur를 덮을수 있는지 (dfs)
3) 덮을 수 있다면 그걸 최소화
-> 최소화
*/
class Solution {
    int ans = 9;
    int[] W;
    // used, 현재까지 처리한 인덱스 cur, 끝 인덱스 end
    void dfs(boolean[] used, int[] dist, int cnt, int cur, int end){
        if(cur > end){
            ans = Math.min(ans, cnt);
            return;
        }
        
        for(int i = cnt; i<dist.length; i++){
            if(used[i])
                continue;
            used[i] = true;
            
            // 현재까지 처리한 부분에 친구 커버 영역까지 추가로 지점 얼마나 커버했는지 세고
            // 자식 dfs에서 cur 상태로 사용하기
            int limit = W[cur] + dist[i];
            int next = cur;
            while(next <= end && W[next] <= limit){
                next++;
            }
            
            dfs(used, dist, cnt+1, next, end);
            // 백트래킹
            used[i] = false;
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        // 원형이니까 오프셋 적용해서 2배로 만들기
        W = new int[weak.length*2];
        for(int i = 0; i<weak.length*2; i++){
            if(i < weak.length){
                W[i] = weak[i];
            }
            else
                W[i] = n+weak[i-weak.length];
        }
        
        // 친구 순열로 연속으로 n 커버하면서 그 친구의 사용 숫자를 최소화하기
        // 최대 8명이라서 dfs할수있음
        for(int i = 0; i<weak.length; i++){
            boolean[] used = new boolean[dist.length];
            dfs(used, dist, 0, i, i+weak.length-1);
        }
        return ans == 9 ? -1 : ans;
    }
}