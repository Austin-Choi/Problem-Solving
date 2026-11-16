/*
코어 번호, 지금 들어가면 다음에 처리 가능해지는 시간 넣고 우선순위큐?
-> 시간초과남

이분탐색으로 n번째 작업이 배정되는 최초 시간 찾기
*/
import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int m = cores.length;
        if(n <= m)
            return n;

        // 가장 오래걸리는 코어로 n개의 작업을 모두 처리했을때 시간 r
        long l = 0;
        long r = 0;
        for(int core : cores)
            r = Math.max(r, (long) core);
        r *= n;
        
        // n개의 작업 이상 처리하는 최소 시간 l
        while(l <= r){
            long mid = (l+r)/2;
            // 처음 m개의 작업은 차례로 배정함
            long count = m;
            for(int core : cores)
                count += mid/core;
            
            if(count >= n)
                r = mid -1;
            else
                l = mid +1;
        }
        
        long time = l;
        
        // time 직전까지 배정된 작업수
        long count = m;
        for(int core : cores){
            count += (time-1)/core;
        }
        
        for(int i = 0; i<m; i++){
            if(time % cores[i] == 0)
                count++;
            if(count == n)
                return i+1;
        }
        return -1;
    }
}