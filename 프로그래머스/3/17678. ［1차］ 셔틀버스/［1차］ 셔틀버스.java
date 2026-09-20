import java.util.*;
/*
콘이 시간표 항상 맨 뒤에 선다는게 아니라 n,t를 고려해서 막차 시간까지 고려했을때 
콘이 출근을 할 수 있는 최대한 뒤의 버스의 최대한 마지막자리에 탄다는거임
-> 1번예제의 경우 버스는 9시에 한번 오고 m=5라서 자리 한개가 남음 그러면 9시에 오면됨
-> 2번 예제의 경우 버스는 9시, 9시10분에 오는데 8시에 온 사람은 9시차 타고 가고 
기다리는 사람이 9시9분 9시 10분에 오는데 콘이 출근하려면 m=2니까 9시 9분에는 와야 9시 9분 2명이 타고 출근가능

1) timetable int로 바꿔서 오름차순 정렬하기
2) n만큼 돌면서 (while), ti (timetable idx) 값이 현재 버스 시간보다 작으면서 cnt가 m 이하일때까지 ti 증가시키기
3) n 마지막에 적당히 콘 시간 계산하기
4) ttos 사용해서 형식맞춰서 리턴하기
*/

class Solution {
    int stot(String time){
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
    
    String ttos(int time){
        String rst = "";
        rst += String.format("%02d", time/60);
        rst += ":";
        rst += String.format("%02d", time%60);
        
        return rst;
    }
    
    public String solution(int n, int t, int m, String[] tt) {
        int st = 60*9;
        int[] T = new int[tt.length];
        for(int i = 0; i<tt.length; i++){
            T[i] = stot(tt[i]);
        }
        Arrays.sort(T);
        
        int ni = 0;
        int ti = 0;
        
        while(ni < n){
            int cur = st + (ni*t);
            int cnt = 0;
            while(ti < T.length && cnt < m && T[ti] <= cur){
                ti++;
                cnt++;
            }
            
            // 마지막 버스에서
            if(ni == n-1){
                // 아직 자리가 남았으면 cur에 도착하는 버스니까 cur가 답이 됨
                if(cnt < m)
                    return ttos(cur);
                // 안남았으면 현재까지 처리한 마지막 크루보다 1분 빨리와서 서야 가장 늦게 타고감
                else
                    return ttos(T[ti-1]-1);
            }
            
            ni++;
        }
        
        return "";
    }
}