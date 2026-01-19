/*
1자리, 2자리
dp[n] = n자리까지 처리했을때 경우의 수
-> 1자리로 끝나는 경우 + 2자리로 끝나는 경우
1자리 -> 현재 값이 0 아니면
2자리 -> 앞이 1이거나 2&& 0~6 이면
 */
import java.util.*;
import java.io.*;
public class Main {
    static long MOD = 1000000L;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        long[] dp = new long[temp.length+1];
        if(temp[0] == '0'){
            System.out.print(0);
            return;
        }
        dp[0] = 1;
        for(int i = 1; i<=temp.length; i++){
            if(temp[i-1] != '0')
                dp[i] = (dp[i] + dp[i-1]) % MOD;

            if(i > 1){
                if(temp[i-2] == '1' || (temp[i-2] =='2' && temp[i-1] >= '0' && temp[i-1] < '7'))
                    dp[i] = (dp[i] + dp[i-2]) % MOD;
            }
        }
        System.out.print(dp[temp.length]);
    }
}
