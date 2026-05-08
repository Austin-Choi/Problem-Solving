/*
dp일거같고 16개인걸 봐서 DAG위의 비트 dp?
dp[i][j] = 마지막 해결한 스테이지가 i이고 힌트권 사용 상태가 j일때 최소 해결 비용
mask = 어떤 힌트 번들을 구매했는지
해당 스테이지에 도달했을때 mask를 보고 힌트 번들 표에서 힌트권 번호 카운팅해서 
cost에 해당하는 값 누적해 나가기??

근데 꼭 dp아니어도 될듯 dp도 완탐인데 
방향이 정해져있고 min갱신해도 될듯

hint 번호 1-based
*/
class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int ans = 16 * 100000 * 16 + 1;
        int N = cost.length;
        int maxMask = 1<<(N-1);
        
        for(int m = 0; m<maxMask; m++){
            int tot = 0;
            int[] cnt = new int[N];
            
            // 번들 적용
            for(int i = 0;i<N-1; i++){
                if((m & (1<<i)) != 0){
                    // 번들 사는 비용
                    tot += hint[i][0];
                    for(int j = 1; j<hint[i].length; j++){
                        cnt[hint[i][j]-1]++;
                    }
                }
            }
            
            //
            for(int s = 0; s<N; s++){
                // cnt -> 지금 mask 상태에서 갖고있는 s 스테이지 힌트권 수
                tot += cost[s][Math.min(cnt[s], N-1)];
            }
            
            ans = Math.min(ans, tot);
        }
        
        return ans;
    }
}