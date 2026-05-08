/*
works의 최대값을 최소화 시켜야 함
만약 works의 전체 합보다 n이 더 크거나 같으면 답은 0

우선순위 큐로 최대 백만번 삽입삭제?
음.. 상수시간 줄이려면 중복된 데이터값 묶어서 처리하기?

- 우선순위 큐 치우고 counting 배열만?

*/
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        int sum = 0;
        // map 느낌으로
        int[] cnt = new int[50001];
        int max = -1;
        
        for(int w : works){
            max = Math.max(w, max);
            cnt[w]++;
            sum += w;
        }

        if(sum <= n)
            return 0;
        
        int curMax = max;
        while(n > 0 && curMax > 0){
            // pq 아니고 직접 idx 움직이는거라 비어있으면 내려야함
            if(cnt[curMax] == 0){
                curMax--;
                continue;
            }
            
            int cc = cnt[curMax];
            if(n >= cc){
                cnt[curMax] = 0;
                cnt[curMax-1] += cc;
                n -= cc;
                curMax = curMax-1;
            }
            else{
                cnt[curMax] = cc-n;
                cnt[curMax-1] += n;
                n = 0;
            }
        }
        
        
        long ans = 0;
        for(int i = 1; i <= curMax; i++){
            if(cnt[i] == 0)
                continue;
            ans += (long)i * i * cnt[i];
        }
        
        return ans;
    }
}