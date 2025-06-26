
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] costs = new int[N];
        int[] effects = new int[N];

        // C 최대가 1000, 고객수 100임
        int[] dp = new int[C+101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0; i <N;i++){
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            effects[i] = Integer.parseInt(st.nextToken());
        }

        // 광고 종류를 하나씩 훈회함
        // 각 광고마다 무한히 사용 가능하니
        // 이 광고를 고려해서 가능한 모든 사람 수에 대해 최소 비용을 갱신
        for(int i = 0; i<N; i++){
            int cost = costs[i];
            int effect = effects[i];

            for(int j = effect; j<dp.length; j++){
                // dp[j - effect] 이전 상태가 도달 불가능하면 갱신 X
                if(dp[j-effect] != Integer.MAX_VALUE){
                    // 해당 광고를 써서 j명을 만들 수 있는지 확인하고 최소화
                    dp[j] = Math.min(dp[j], dp[j-effect]+cost);
                }
            }
        }

        //목표값 이상의 인원수를 채운 것들중 최소 cost 찾기
        int ans = Integer.MAX_VALUE;
        for(int i = C; i<dp.length; i++){
            ans = Math.min(ans, dp[i]);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
