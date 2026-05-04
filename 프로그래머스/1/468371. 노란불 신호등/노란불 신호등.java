/*
현재의 시간을 넣고 노란불의 구간에 있는지 알려주는 함수 구현해야함
안되는 경우 : 최대공배수 범위 벗어나는 경우
*/
class Solution {   
    // 계산할때 0base라 출력할때 ct+1해야함
    boolean isYellow(int[] a, int ct){
        if(ct < a[0])
            return false;
        int rate = a[0]+a[1]+a[2];
        ct -= a[0];
        ct %= rate;
        // 주기는 노란색부터 시작
        if(ct < a[1])
            return true;
        return false;
    }
    
    public int solution(int[][] ss) {
        int ct = 0;
        while(true){
            boolean temp = true;
            for(int[] s : ss){
                temp &= isYellow(s, ct);
                if(!temp)
                    break;
            }
            if(temp)
                break;
            ct++;
            
            if(ct > 2_000_000)
                return -1;
        }
        return ct+1;
    }
}