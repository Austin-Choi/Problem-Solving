import java.util.*;
/*
왼->오, 하나씩 건너뛰면서 왼쪽의 것을 제거함
오->왼, 하나씩 건너뛰면서 오른쪽의 것을 제거함
하나 남을때까지 반복
남은 하나 출력

그대로 시뮬하면 터질거같고
1
2
4
8

1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
-> 2 4 6 8 10 12 14 16 18
4 8 12 16
-> 8 16
8


한번 할때마다 floor(n/2)개 남음
시작숫자 끝숫자 저장..
시작숫자가 결국 하나 남을것
*/

class Solution {
    public int lastRemaining(int n) {
        boolean isLeft = true;
        int start = 1;
        int step = 1;
        while(n > 1){
            if(isLeft || n %2 == 1){
                start += step;
            }
            n /= 2;
            step *= 2;
            isLeft = !isLeft;
        }
        return start;
    }
}