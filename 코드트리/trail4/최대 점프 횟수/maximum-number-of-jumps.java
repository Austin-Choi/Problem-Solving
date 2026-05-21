import java.util.*;
import java.io.*;

/*
dp[i] = i에서 시작했을때 최대 미래 점프 횟수
미래 값이 계산되어있어야 하므로 뒤에서부터 시작해야함
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] a = new int[N];
        for(int i = 0 ;i<N; i++){
            a[i] = read();
        }

        int[] dp = new int[N];
        for(int i= N-1; i>=0; i--){
            int jump = a[i];
            for(int j = i+1; j<=i+jump && j<N; j++){
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        System.out.print(dp[0]);
    }
}