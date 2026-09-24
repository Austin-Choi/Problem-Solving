/*
can 함수 -> long x분 동안 주어진 심사관들로 n명을 모두 처리할 수 있는지
7 10 14 20 21 28
근데 매번 한명 처리하고 나서 다음 심사대를 고를때 최대 10만개의 심사관을 검색할 순 없음 
n이 최대 10^10이라서 
x분동안 처리할수 있는 사람 수는 x/times[i] 이거 다 더해서 x 넘으면 true
*/
import java.util.*;
class Solution {
    int N;
    int[] T;
    
    boolean can(long x){
        long tot = 0;
        for(int i = 0; i<T.length; i++){
            tot += (x / T[i]);
            if(tot >= N)
                return true;
        }
        return false;
    }
    
    public long solution(int n, int[] times) {    
        N = n;
        T = times;
        
        long ans = -1;
        long l = 0;
        long r = n * 1L * 1_000_000_000;
        while(l<r){
            long mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid;
            }
            else 
                l = mid + 1;
        }
        return ans;
    }
}