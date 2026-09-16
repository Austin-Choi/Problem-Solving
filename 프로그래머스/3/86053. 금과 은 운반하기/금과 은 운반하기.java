import java.util.*;
/*
편도 이동시간 t임 -> 트럭이 도시에 도착하면 되는거라 1번만 가면 되는건 편도 한번만으로 셈
-> 입력 2에서 0번도시 4시간동안 70kg 배달 + 1번도시 8시간동안 20kg 배달 8시간
-> 입력 2에서 2번도시 249번 왕복 (498) + 1번 편도 -> 499 
-> 입력 1에서 
동시에 이루어질 수 잇음 -> 도시마다 트럭이 각각 존재하기 때문에 
-> 걸린 시간중에 최대치를 리턴하기

-> T 시간동안 모든 금과 은을 조달할 수 있는가? can함수
1) 이분탐색 범위 잡기 -> 최대 시간 T = 
2) can 함수
*/

class Solution {
    //최대 X 시간동안 모든 재료를 운반할 수 있는가
    boolean can(long X, int a, int b, int[] g, int[] s, int[] w, int[] t){
        int N = g.length;
        long gold = 0;
        long silver = 0;
        long total = 0;
        
        for(int i = 0; i<N; i++){
            // X시간동안 도시에 도착할 수 있는 횟수
            long cnt = X / (2L * t[i]);
            // X에서 왕복이동하고 시간 t[i]보다 더 남으면 편도로 한번더가능
            if(X % (2L * t[i]) >= t[i])
                cnt++;
            
            long cap = cnt * w[i];
            long gg = Math.min((long) g[i], cap);
            long ss = Math.min((long) s[i], cap);
            // 금+은 동시 운반 가능하므로 
            long tt = Math.min((long) g[i] + s[i], cap);
            
            gold += gg;
            silver += ss;
            total += tt;
        }
        
        return gold >= a && silver >= b && total >= ((long)a + b);
    }
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long l = 0;
        long r = 1_000_000_000_000_000L;
        long ans = 0;
        while(l<=r){
            long mid = (l+r)/2;
            if(can(mid,a,b,g,s,w,t)){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid+1;
        }
        return ans;
    }
}