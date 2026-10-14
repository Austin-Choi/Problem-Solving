// 3 1 20
/*
6 +2
2 +2
1 +1
2
*/
class Solution {
    public int solution(int a, int b, int n) {      
        int tot = 0;
        int rem = 0;
        while(true){
            int gift = n/a;
            if(gift == 0)
                break;
            rem = n%a;
            tot += gift*b;
            n = gift * b + rem;
        }
        
        return tot;
    }
}