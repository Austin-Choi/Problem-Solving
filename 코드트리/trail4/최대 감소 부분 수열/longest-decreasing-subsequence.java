import java.util.*;
import java.io.*;

/*
dp[i] = i까지 진행했을때 가장 긴 길이
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] arr = new int[N];
        for(int i= N-1; i>=0; i--){
            arr[i] = read();
        }

        int[] dp = new int[N];
        for(int i = 0; i<N; i++){
            dp[i] = 1;
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int ans = 0;
        for(int n : dp){
            ans = Math.max(ans, n);
        }
        System.out.print(ans);
    }
}