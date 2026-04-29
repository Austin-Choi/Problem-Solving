/*
정수 배열이라 음수까지 커버함
라빈카프 느낌으로 ㄱ
*/
class Solution {
    public int[] solution(int n, int t) {
        int sum = 0;
        int start = -t;
        for(int i = 0; i<n; i++){
            sum += (start+i);
        }
        int e = t;
        while(true){
            if(sum == t)
                break;
            sum -= start;
            sum += (start+n);
            start++;
        }
        int[] rst = new int[n];
        for(int i=0; i<n; i++){
            rst[i] = start+i;
        }
        return rst;
    }
}