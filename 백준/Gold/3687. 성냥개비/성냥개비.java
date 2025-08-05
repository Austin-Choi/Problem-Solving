import java.io.*;
public class Main {
    static final int[] cost = {2,3,4,5,6,7};
    static final String[] nums = {"1","7","4","2","0","8"};
    static String makeMax(int n){
        StringBuilder sb = new StringBuilder();
        if(n%2 == 1){
            sb.append(7);
            for(int i = 0; i< (n/2)-1; i++){
                sb.append(1);
            }
        }
        else{
            for(int i = 0; i< (n/2); i++){
                sb.append(1);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb2 = new StringBuilder();

        String[] dp = new String[101];
        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        // 0으로 하면 0이 앞에옴
        dp[6] = "6";
        dp[7] = "8";
        dp[8] = "10";
        
        /*
        0이 계속 앞에 붙는 문제
        -> 맨 앞자리에 오는 0은 선택하면 안됨
        -> dp[i-k]가 0으로 시작하면 안됨..
        -> 따로 String 배열 쓰기
        
        dp[n] = String상 min(dp[n-cost[k]] + nums[k])
         */
        for(int i = 9; i<=100; i++){
            for(int j = 0; j<cost.length; j++){
                if (i - cost[j] < 0 || dp[i - cost[j]] == null) 
                    continue;
                String candidate = dp[i - cost[j]] + nums[j];
                if (dp[i] == null
                        || candidate.length() < dp[i].length()
                        || (candidate.length() == dp[i].length() && candidate.compareTo(dp[i]) < 0)) {
                    dp[i] = candidate;
                }
            }
        }

        for(int t = 0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            String max = makeMax(N);
            String min = dp[N];
            sb2.append(min).append(" ").append(max).append("\n");
        }
        bw.write(sb2.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
