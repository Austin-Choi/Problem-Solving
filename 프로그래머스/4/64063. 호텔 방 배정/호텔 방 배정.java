/*
set으로 이미 사용된 방 번호를 관리하고 
여기 있으면 1씩 증가시키기?

1~k까지 방이 있고 그 방을 한번 쓰게 되면 
+1 한 방이 됨 
그렇게 해서 쭉 배열에서 찾아가서 rn[i] == i를 만족할때 출력? map.get i == i
1 3 4 1 3 1이 입력으로 들어오고 
방이 10까지 있다고 치면
[1] = 1
[1] = 2로 변하고
[3] = 4로 변하고
[4] = 5로변하고
[1] = 2 -> [i] != i 니까 [[1]] = 3으로 변하고

*/

import java.util.*;
import java.io.*;

class Solution {
    public long find(Map<Long,Long> m, long x){
        if(!m.containsKey(x)){
            m.put(x,x+1);
            return x;
        }
        long next = find(m, m.get(x));
        m.put(x, next);
        return next;
    }
    public long[] solution(long k, long[] rn) {
        long[] ans = {};
        Map<Long, Long> m = new HashMap<>();
        
        int N = rn.length;
        ans = new long[N];
        
        for(int i = 0; i<N; i++){
            ans[i] = find(m, rn[i]);
        }
        return ans;
    }
}