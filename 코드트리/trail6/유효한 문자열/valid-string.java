import java.util.*;
import java.io.*;

/*
int[i][j] dp = 구간 i,j에서의 유효한 문자열 가짓수 
i+1,k-1 k+1,j
*/

public class Main {
    static final int MOD = 10_007;

    // 두 문자가 짝이 될 수 있는지
    static int match(char a, char b){
        int cnt = 0;
        if((a == '(' || a == '?') && (b == ')' || b == '?'))
            cnt++;
        if((a == '{' || a == '?') && (b == '}' || b == '?'))
            cnt++;
        if((a == '[' || a == '?') && (b == ']' || b == '?'))
            cnt++;
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int N = s.length;

        int[][] dp = new int[N][N];
        for(int i = 0; i<N; i++){
            dp[i][i] = 1;
        }

        for(int len = 2; len <= N; len++){
            for(int i = 0; i+len-1 < N; i++){
                int j = i+len-1;
                // k는 i와 짝이 되어야 함
                // A가 유효하면 [a] 이런식으로 감
                for(int k = i+1; k<=j; k+=2){
                    int cnt = match(s[i], s[k]);
                    if(cnt == 0)
                        continue;

                    /*
                    ()에서는 안쪽이 빈 문자열인데 
                    이때는 i+1 > k-1임 따라서 빈 문자열도 유효하다 했으니 1
                    오른쪽도 이때는 k+1 > j 이므로 1
                    */
                    int left = (i+1 < k-1) ? dp[i+1][k-1] : 1;
                    int right = (k+1 < j) ? dp[k+1][j] : 1;
                    dp[i][j] = (dp[i][j] + (cnt * left * right)) % MOD;
                }
            }
        }

        System.out.print(dp[0][N-1]);
    }
}