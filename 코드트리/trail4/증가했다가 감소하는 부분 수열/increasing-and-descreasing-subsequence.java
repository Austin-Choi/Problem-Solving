import java.util.*;
import java.io.*;

/*
dp1[i] = i까지 봤을때 증가 수열 최대 길이
dp2[i] = i부터 시작했을때 감소 수열 최대 길이 
-> 그냥 뒤에서부터 증가수열 최대 길이
dp[i] = max(dp1[i] + dp2[i])

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] in  = new int[N];
        int[] rev  = new int[N];
        for(int i = 0; i<N; i++){
            int cur = read();
            in[i] = cur;
            rev[N-i-1] = cur;
        }

        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        for(int i = 0; i<N; i++){
            for(int j = i+1; j<N; j++){
                if(in[i] < in[j])
                    dp1[j] = Math.max(dp1[j], dp1[i]+1);
                    
                if(rev[i] < rev[j]){
                    int ni = N-1-i;
                    int nj = N-1-j;
                    dp2[nj] = Math.max(dp2[nj], dp2[ni]+1);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            ans = Math.max(dp1[i] + dp2[i]-1, ans);
        }

        System.out.print(ans);
    }
}