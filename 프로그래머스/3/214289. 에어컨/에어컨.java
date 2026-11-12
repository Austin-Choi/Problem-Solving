/*
t1<=승객이 타있을때의 온도<=t2
소비전력을 최소화하려면 
이거 dp로 해야할거같고 매번 선택할수 있는 상태는 
고려해야하는건 승객 탑승했을때 t1<= <=t2를 맞추기 위한 최적의 희망온도를 처음 시작 실외온도로부터 계산해둬야함?

dp[i][temp] = 현재 i분이고 현재 희망온도가 temp일때 지금까지의 최소 소비전력 합
temp-1 : 에어컨 켜서 내리거나, 실외가 현재 temp보다 추워서 꺼서 내리기
temp : 희망온도=에어컨 온도이므로 b이거나 실외랑 현재 temp랑 같아서 0
temp+1 : 에어컨 켜서 올리거나, 실외가 현재 temp보다 더워서 꺼서 올리기
*/
import java.util.*;

class Solution {
    // T 실외온도
    public int solution(int T, int t1, int t2, int a, int b, int[] onboard) {
        // 온도 범위 -10~40이므로
        int offset = 10;
        T += offset;
        t1 += offset;
        t2 += offset;
        
        int N = onboard.length;
        int[][] dp = new int[N][51];
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][T] = 0;
        
        for(int i = 0; i<N-1; i++){
            for(int temp = 0; temp<51; temp++){
                if(dp[i][temp] == Integer.MAX_VALUE)
                    continue;
                
                // 에어컨 켜기 
                // -1,0,+1 세가지 가능
                for(int d = -1; d<=1; d++){
                    int next = temp+d;
                    if(next < 0 || next > 50)
                        continue;
                    // 만약 손님탓으면 t1 t2 사이여야함
                    if(onboard[i+1] == 1){
                        if(next < t1 || t2 < next)
                            continue;
                    }
                    // 온도 같으면 b만큼 소모, 다르면 a만큼
                    dp[i+1][next] = Math.min(dp[i+1][next], dp[i][temp] + ((d==0) ? b : a));
                }
                
                // 에어컨 끄기
                int offTemp = temp;
                if(offTemp < T)
                    offTemp++;
                else if(offTemp > T)
                    offTemp--;
                
                if(offTemp < 0 || offTemp > 50)
                    continue;
                
                // 승객이 탑승하지 않거나, off한 온도가 희망온도 범위를 만족한다면 off 가능
                if(onboard[i+1] == 0 || (t1 <= offTemp && offTemp <= t2))
                    dp[i+1][offTemp] = Math.min(dp[i+1][offTemp], dp[i][temp]);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<51; i++){
            ans = Math.min(ans, dp[N-1][i]);
        }
        return ans;
    }
}