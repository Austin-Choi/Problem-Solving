import java.util.*;
/*
시각 변환 함수 하나 필요하고 logs 시작 끝 시각으로 변환해야하고
차분 배열을 활용한 누적합으로 함
-> 시작은 +1, 끝은 -1
-> 광고 누적 시청시간이 최대 인 곳을 찾기
*/

class Solution {
    // String.format으로 패딩처리
    String ttos(int time){
        String rst = "";
        rst += String.format("%02d", time/3600);
        rst += ":";
        time %= 3600;
        rst += String.format("%02d", time/60);
        rst += ":";
        time %= 60;
        rst += String.format("%02d", time);
        return rst;
    }

    // String to Time(int)
    int stot(String time){
        int rst = 0;
        StringTokenizer st = new StringTokenizer(time, ":");
        rst += Integer.parseInt(st.nextToken()) * 3600;
        rst += Integer.parseInt(st.nextToken()) * 60;
        rst += Integer.parseInt(st.nextToken());
        return rst;
    }
    
    // -기준으로 잘라서 시작 끝 로그로 변환
    int[][] convertLog(String[] logs){
        int[][] rst = new int[logs.length][2];
        for(int i = 0; i<logs.length; i++){
            String log = logs[i];
            StringTokenizer st = new StringTokenizer(log, "-");
            rst[i][0] = stot(st.nextToken());
            rst[i][1] = stot(st.nextToken());
        }
        return rst;
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int pt = stot(play_time);
        int at = stot(adv_time);
        int[][] ls = convertLog(logs);
        
        // 차분배열 만들기
        int[] cnt = new int[pt+1];
        for(int i = 0; i<logs.length; i++){
            cnt[ls[i][0]]++;
            cnt[ls[i][1]]--;
        }
        // cnt[i] = i초부터 i+1초까지 시청한 누적 사람 수
        for(int i = 1; i<=pt; i++){
            cnt[i] += cnt[i-1];
        }
        
        // sum[x] = 0~x초까지의 누적 시청시간
        // long으로 해야함!!
        long[] sum = new long[pt+1];
        for(int i = 1; i<=pt; i++){
            sum[i] = sum[i-1] + cnt[i-1];
        }
        
        // 슬라이딩 윈도
        int ans = 0;
        long maxWatch = 0;
        for(int i = 0; i<=pt-at; i++){
            if(maxWatch < sum[i+at] - sum[i]){
                ans = i;
                maxWatch = sum[i+at] - sum[i];
            }
        }
        
        return ttos(ans);
    }
}