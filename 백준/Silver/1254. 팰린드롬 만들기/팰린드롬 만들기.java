/*
안쪽이 팰린드롬이면 바깥쪽만 체크하면 팰린드롬임 -> 단조성 띄니까 dp적용가능
dp[l][r] = l,r 글자 같을때 구간 길이 2 이하면 true이고 3 이상이면 안쪽에 있는거 전파시킴
pal[i] = 0~i에 붙일 최소 글자 수
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = s.length();
        boolean[][] dp = new boolean[N][N];

        for(int len = 1; len<=N; len++){
            for(int l = 0; l+len-1 < N; l++){
                int r = l+len-1;
                if(s.charAt(l) == s.charAt(r)){
                    if(len <= 2)
                        dp[l][r] = true;
                    else
                        dp[l][r] = dp[l+1][r-1];
                }
            }
        }

        int[] pal = new int[N];
        for(int i = 0; i<N; i++){
            int best = 51;
            for(int k = 0; k<=i; k++){
                if(dp[k][i]){
                    best = Math.min(best, k);
                    break;
                }
            }
            pal[i] = best;
        }

        System.out.println(N+pal[N-1]);
    }
}
