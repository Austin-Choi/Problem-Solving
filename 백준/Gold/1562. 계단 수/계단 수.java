import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 길이가 N이고 끝나는 숫자가 j고 이제까지 사용된 수가 비트마스크일때 계단수의 수
        int[][][] dp = new int[N+1][10][1 << 10];

        // 길이 1일때 맨앞 0 못와서 이렇게 초기화
        for(int i = 1; i<10; i++){
            dp[1][i][1 << i] = 1;
        }

        for(int i = 2; i<=N; i++){
            // 현재 끝나는 숫자
            for(int j = 0; j<10; j++){
                // 직전 자리의 숫자
                for(int k : new int[]{j-1, j+1}){
                    if(k < 0 || k > 9)
                        continue;
                    // 이전까지 사용된 숫자들을 꺼내서
                    for(int mask = 0; mask < (1<<10); mask++){
                        // 다음에 붙일 새 숫자를 붙여서 비트마스크로 상태 표현함
                        int newmask = mask | (1<<j);
                        // 이전 자리에서 k로 끝나고 mask를 썻던 경우에
                        // j를 붙이면 newmask 상태로 이어진다
                        dp[i][j][newmask] = (dp[i][j][newmask] + dp[i-1][k][mask]) % 1000000000;
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<10; i++){
            ans = (ans + dp[N][i][(1 << 10) -1]) % 1000000000;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}