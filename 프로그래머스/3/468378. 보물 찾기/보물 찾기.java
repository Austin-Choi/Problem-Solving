/*
money에 대해서 최적화할 필요는 없고 그 이하에서 해결하면 됨
1~W 중 하나의 열을 고르고 굴착하면 해당 열의 세로가 다 파여버리고 
-> 굴착 가능한 깊이만큼 비용이 발생하는데 depth는 최대 깊이만 주어짐.. 비용산정을 어케함?
-> money에 대해서 구간 dp, minimax dp
뭐 왼쪽 오른쪽 0 이런식으로 반환하는거 보면 중앙에서 시작해서 결과 보고 0 나올때까지 하면 될거같은데
*/
import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {      
        int l = 1;
        int r = depth.length;
        
        // 그냥 이분탐색으론 안될거 같고 
        // 해당 구간에서의 최적해를 미리 구해놓고 거길 mid로 하는 식으로 재활용
        // dp는 최소 돈 값을 저장하고 best는 그 최소 위치를 따로 저장함
        int[][] dp = new int[r+2][r+2];
        int[][] best = new int[r+2][r+2];
        for(int len = 1; len <= r; len++){
            // 1-based로 통일
            for(int i = 1; i+len-1<=r; i++){
                int j = i+len-1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // 해당 구간에서 최악의 경우가 가장 작은 경우를 구하기
                // minimax dp
                for(int k = i; k<=j; k++){
                    // 1-based니까 현재 cost는 depth[k-1],
                    // 현재를 무조건 파고 왼쪽, 오른쪽 중에 큰 값 더하면 최악의 값 
                    int cost = depth[k-1] + Math.max(dp[i][k-1], dp[k+1][j]);
                    // 그 최악의 값이 최소가 되는 곳 구하기
                    if(cost < dp[i][j]){
                        dp[i][j] = cost;
                        best[i][j] = k;
                    }
                }
            }
        }
        
        while(l<=r){
            //int mid = (l+r)/2;
            int mid = best[l][r];
            int rst = excavate.apply(mid);
            if (rst == 0)
                return mid;
            else if(rst == -1)
                r = mid -1;
            else
                l = mid + 1;
        }
        return 0;
    }
}