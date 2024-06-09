import java.util.*;

class Solution {
    public List solution(int l, int r) {
        ArrayList<Integer> ans = new ArrayList<>();
        //결과는 1 10 11 100 101 110 ... 이진법으로 1 2 3 4 5 표현한것과 같음
        // 1 000 000 은 이진법으로 2의 6승 64임
        // Integer.toBinaryString 활용
        for(int i = 1; i<=64; i++){
            int n = Integer.parseInt(Integer.toBinaryString(i))*5;
            if(l<=n && n<=r)
                ans.add(n);
            if(n>r)
                break;
        }
        if(ans.isEmpty())
            ans.add(-1);
        
        return ans;
    }
}