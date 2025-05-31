import java.io.*;
import java.util.StringTokenizer;

/*
최대 4가 되고
최소 개수의 제곱수 합이라면

예를 들어 50000은
40000 + 10000
으로 2로 할 수 있음

dp 설계
1차원 dp, 인덱스는 실제 수, value는 최소 갯수
 */

public class Main {
    private static int[] dp = new int[50001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        dp[0] = 0;

        int num = Integer.parseInt(br.readLine());

        // 현재 구하려는 수보다 작은 수중에 제곱수를 빼고 남은 상태에 +1하기
        for(int i = 1; i<=num; i++){
            // 라그랑주의 정리
            dp[i] = 4;
            for(int j = 1; j*j<= i; j++){
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        sb.append(dp[num]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
