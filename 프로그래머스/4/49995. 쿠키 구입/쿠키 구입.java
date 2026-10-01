/*
연속으로 이어진 어느 두 구간이 같은 값을 가지고 그 합이 최대가 될때 그 값을 구하기
누적합 전처리하고 투포인터로 두 용액처럼 풀기
*/
import java.util.*;
class Solution {
    public int solution(int[] c) {
        int ans = 0;
        int N = c.length;
        // 누적합
        int[] p = new int[N+1];
        for(int i = 1; i<p.length; i++){
            p[i] = p[i-1] + c[i-1];
        }
        
        // m은 중단점, l은 왼쪽 끝 r은 오른쪽 맨 끝
        // 왼쪽 크기 = m+1 - l
        // 오른쪽 크기 = r - m+1
        // 둘이 크기 같으면 둘다 한칸씩 늘려보고
        // 다르면 부족한 쪽만 늘려봄
        for(int m = 0; m<N-1; m++){
            int l = m;
            int r = m+1;
            while(l >= 0 && r < N){
                int left = p[m+1] - p[l];
                int right = p[r+1] - p[m+1];
                
                if(left == right){
                    ans = Math.max(ans, left);
                    l--;
                    r++;
                }
                else if(left < right)
                    l--;
                else
                    r++;
            }
        }
        return ans;
    }
}