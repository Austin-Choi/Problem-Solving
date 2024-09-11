import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        int[] music = new int[2001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            music[i] = Integer.parseInt(st.nextToken());
        }
        // 각자의 상태를 저장해야 하므로 2차원 배열
        // 상덕 A, 희원 B
        // 조합인데 입력 순서 지켜야함 (노래라서)
        // 그니까 마지막에 부른 음만 보고 판단해주면 됨.
        // 인덱스는 music의 인덱스와 같다.
        // 따라서, dp[A가 마지막으로 부른 음][B가 마지막으로 부른 음] 의 최소값 저장
        dp = new int[2001][2001];

        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 초기값 세팅
        // 음이 0일때는 당연히 최소합이 0이기 때문에
        dp[0][1] = 0;
        dp[1][0] = 0;

        /*
            next = Math.max(A,B)+1;
         */


        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                if(i == j)
                    continue; // 같은음은 어차피 피로도 0이니까 스킵
                // 상덕이든 희원이든 음은 반드시 순서대로 불러야 하므로
                // 현재까지 두사람이 부른 중 더 나중에 부른 사람의 음의 바로 다음것(+1 해주는 이유임)이 next가 되어야 함
                int next = Math.max(i, j)+1;
                // indexBound 오류 발생
                // -> 해결
                if(next > N)
                    continue;

                if(i == 0 || j == 0)
                    music[0] = music[next]; // 0번째 음을 불렀을 경우 힘든 정도가 0이므로 abs 할 필요 없이 music[next] 값이 그대로 들어가게 된다.
                dp[next][j] = Math.min(dp[next][j], dp[i][j] + Math.abs(music[i]-music[next]));
                // B가 다음으로 부를때
                dp[i][next] = Math.min(dp[i][next], dp[i][j] + Math.abs(music[j]-music[next]));
            }
        }

        int answer = Integer.MAX_VALUE;
        // 노래를 다 불렀다는 것은 둘 중 한 사람이 마지막 음을 불렀다는 것이므로
        for(int i = 0; i<=N; i++){
            // 상덕이가 마지막음을 불렀다
            answer = Math.min(answer, dp[N][i]);
            // 희원이가 마지막음을 불렀다
            answer = Math.min(answer, dp[i][N]);
        }
        bw.write(answer+"\n");
        bw.close();
        br.close();
    }
}
