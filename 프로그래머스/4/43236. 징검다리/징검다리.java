import java.util.*;
/*
파라메트릭 서치?
distance를 right으로 잡고 left를 0
모든 남은 바위들의 간격이 mid 이상인지 보기
-> 되면 더 늘리고 안되면 줄이기

그리디(can)
일단 rocks를 정렬함
조합 방법을 직접 찾는 방향이 아니라
현재 간격을 비교해서 x 미만이면 x 이상으로 못만드니까 제거해야함
그렇게 해서 제거한 수가 n개 이하인지 보기
-> 덜 제거해서 가능하면 n개도 가능인거임
-> 최소 거리는 더 나빠지지 않음
*/

class Solution {
    // n개 삭제후 각 지점 사이의 거리의 최솟값이 x 이상으로 만들수 있는지
    boolean can(int dist, int x, int[] rocks, int n){
        int cnt = 0;
        int prev = 0;
        for(int i = 0; i<rocks.length; i++){
            if(rocks[i]-prev < x){
                cnt++;
                continue;
            }
            else
                prev = rocks[i];
            
            if(cnt > n)
                return false;
        }
        if(dist - prev < x)
            cnt++;
        
        return cnt <= n;
    }
    
    public int solution(int d, int[] rocks, int n) {
        int ans = 0;
        int l = 0;
        int r = d;
        Arrays.sort(rocks);
        while(l<=r){
            int mid = (l+r)/2;
            if(can(d, mid, rocks, n)){
                ans = mid;
                l = mid + 1;
            }
            else
                r = mid-1;
        }
        return ans;
    }
}