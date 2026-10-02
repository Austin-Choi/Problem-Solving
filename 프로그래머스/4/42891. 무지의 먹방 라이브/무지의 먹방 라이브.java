/*
회전판에 n개 음식이 있음
각 음식마다 1초만 음식을 섭취하고 음식마다 다 먹는 시간이 있음 
-> 음식 다먹는시간 (최대 방문 가능 횟수)
K 초 후에 방송이 중단되고 그다음 먹어야 하는 음식 번호 
-> 근데 중간에 다 없어진 음식이 있으면 n이 줄어듬

K를 n으로 나머지 연산해서 푸는 그런거 같고
K가 long 으로 주어져서 시뮬레이션은 못풀거같음

food times 정렬하고 
food times로 제일 작은거 골라내고 time * N만큼 현재 소비량 맞추고 
K >= 현재 소비량 계속 진행
K < 현재 소비량이면 K % N 번호가 답
반복
*/
import java.util.*;
class Solution {
    public int solution(int[] ft, long k) {
        int ans = -1;
        int N = ft.length;
        
        int[][] data = new int[N][2];
        for(int i = 0; i<N; i++){
            data[i] = new int[]{ft[i], i+1};
        }
        Arrays.sort(data, Comparator.comparingInt(a->a[0]));
        
        int count = N;
        long prev = 0;
        for(int i = 0; i<data.length; i++){
            int ct = data[i][0];
            int ci = data[i][1];
            
            long needTime = (ct - prev) * count * 1L;
            if(k >= needTime){
                k -= needTime;
                prev = ct;
                // 같은 종류 음식들 제거
                int j = i;
                while(j < data.length && data[j][0] == ct){
                    count--;
                    j++;
                }
                i = j-1;
            }
            else{
                Arrays.sort(data, Comparator.comparingInt(a->a[1]));
                int idx = (int)(k % count);
                for(int j = 0; j<N; j++){
                    if(data[j][0] > prev){
                        if(idx == 0)
                            return data[j][1];
                        idx--;
                    }
                }
            }
        }
        
        return ans;
    }
}