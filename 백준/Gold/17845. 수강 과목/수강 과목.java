import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static int[][] infos;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        infos = new int[K][2];
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            infos[i] = new int[]{v,c};
        }

        // 0-1배낭문제
        // dp[i] = i시간 공부했을때 가능한 중요도의 최대 합
        // 각 과목 한번만 고를수 있어서 시간 뒤에서부터 순회함 거기다가 현재 val 더해봐서 갱신함
        int[] dp = new int[N+1];

        for(int i = 0; i<K; i++){
            int val = infos[i][0];
            int time = infos[i][1];
            for(int t = N; t>=time; t--){
                dp[t] = Math.max(dp[t], dp[t-time] + val);
            }
        }
        System.out.print(dp[N]);
    }
}
