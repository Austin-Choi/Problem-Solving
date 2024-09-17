
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        // dp : 각 인덱스에 도달할 때 기준으로 현재 가장 긴 증가하는 부분 수열의 길이를 저장
        int[] dp = new int[N];
        int[] pool = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            pool[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1; // 첫번째 원소까지 해서 뽑으면 무조건 가장 긴 증가하는 부분 수열의 길이는 1이므로
        for(int i = 1; i<N; i++){
            dp[i] = 1; // 자기 자신 하나 뽑는 경우는 항상 있으므로 1로 초기화
            for(int j = 0; j<i; j++){
                // pool[j] < pool[i] 이고 -> 현재 마지막으로 고른 원소(ㅑ)가 이제까지 나온 원소들과 비교해서 크다면
                // dp[j] >= dp[i]라면 -> 현재 마지막으로 고른 원소 기준의 길이(ㅑ)에는
                // 앞에 있는 원소들에 대해서 저장되어있는 가장 긴 증가하는 부분 수열의 값을 포함해야 하므로
                if(pool[j] < pool[i] && dp[j]>=dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // 마지막에 뽑은 원소에 따라서 값이 다르므로 max값 구해야함
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 배열을 List로 변환
        // pq.addAll(Arrays.stream(dp).boxed().toList());
        // 백준 자바 버전 너무 낮아서 이거 못 씀 ㅋㅋ

        int answer = -1;
        for(int i : dp){
            answer = Math.max(i, answer);
        }

        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
