/*
현재까지의 최대와 전체 최대를 저장해서 갱신
펄스 수열이 2가지이므로 현재 최대를 2개로
-> 카데인 알고리즘 활용
*/
class Solution {
    public long solution(int[] sequence) {
        long c1 = 0;
        long c2 = 0;
        long max = Long.MIN_VALUE;

        for(int i = 0; i<sequence.length; i++){
            int num = (i%2 == 0) ? sequence[i] : -sequence[i];
            c1 = Math.max(num, c1+num);
            c2 = Math.max(-num, c2-num);
            max = Math.max(max, Math.max(c1,c2));
        }
        
        return max;
    }
}