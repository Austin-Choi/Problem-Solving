import java.util.*;
/*
끝지점 기준으로 정렬해서 
최대한 커버해야하니까 항상 끝에 달고 
지금 보는 차량의 구간의 시작지점이 현재 cam위치보다 뒤면 
cam 갯수 +1 하고 그 구간 끝에 cam 달아주기
*/

class Solution {
    public int solution(int[][] in) {
        int ans = 0;
        Arrays.sort(in, Comparator.comparingInt(a->a[1]));
        int camPos = -30001;
        for(int[] p : in){
            if(p[0] > camPos){
                ans++;
                camPos = p[1];
            }
        }
        return ans;
    }
}