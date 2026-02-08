/*
이분탐색으로 잔 시간 tt를 고르고
결정함수에서 주어진 시간(배낭크기)안에서 최대 가치(여기서는 W점 이상) 이 가능한지 보기
dp[value]는 너무커서 터짐
dp[time] = 최대 점수
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,T,W;
    static int[][] info;

    static boolean can(int tt){
        int bag = T - tt;
        int[] dp = new int[bag+1];

        for(int i = 0; i<N; i++){
            int cost = Math.max(0, info[i][0]-tt);
            int val = info[i][1];

            for(int time = bag; time >=cost; time--){
                dp[time] = Math.max(dp[time], dp[time-cost] + val);
            }
        }
        int max = -1;
        for(int v : dp){
            max = Math.max(max, v);
        }
        return max >= W;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        info = new int[N][2];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = T;
        int ans = -1;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        System.out.print(ans);
    }
}
