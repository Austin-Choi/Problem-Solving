/*
억억단이면 1부터 1억*1억까지에서 long
그리고 등장 횟수는 1~1억 * 1~1억 의 조합중에서 결과니까 
e가 500만이라 e의 조합을 다해보는건 말도안됨
약수의 갯수
에라토스테네스의 체 활용하기
*/
import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        // 약수갯수 세기
        int[] count = new int[e+1];
        // sieve
        for(int i = 1; i<=e; i++){
            for(int j = i; j<=e; j+=i){
                count[j]++;
            }
        }
        
        // best[i] = [i,e]에서 가장 많이 나오는 숫자 자체
        int[] best = new int[e+1];
        best[e] = e;
        for(int i = e-1; i>=1; i--){
            if(count[i] >= count[best[i+1]])
                best[i] = i;
            else
                best[i] = best[i+1];
        }
        
        int[] ans = new int[starts.length];
        for(int i = 0; i<starts.length; i++)
            ans[i] = best[starts[i]];
        return ans;
    }
}