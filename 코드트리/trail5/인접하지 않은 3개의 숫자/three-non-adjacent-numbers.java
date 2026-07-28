import java.util.*;
import java.io.*;

/*
dp[N+1][4]
dp[i][k] = i까지 처리했을때, 현재 선택 수가 k일때 최대 합
dp[i][k] = max(dp[i-1][k], dp[i-2][k-1] + A[i-1])
-> 1-based dp

---------------------
합이 최대로 되도록 하려면
최적의 선택은 
0~i-2까지의 최대값 + A[i] + i+2~N-1까지의 최댓값

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int[] p = new int[N+1];
        int[] s = new int[N+1];
        for(int i = 1; i<=N; i++){
            p[i] = Math.max(p[i-1], A[i-1]);
        }
        for(int i = N-1; i>=0; i--){
            s[i] = Math.max(s[i+1], A[i]);
        }

        int ans = 0;
        // prefix suffix 인덱스 의미 잘 생각하기
        for(int i = 2; i<N-2; i++){
            ans = Math.max(ans, p[i-1] + A[i] + s[i+2]);
        }
        System.out.print(ans);
    }
}