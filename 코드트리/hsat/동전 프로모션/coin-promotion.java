import java.util.*;
import java.io.*;

/*
A 동전은 여러번 사용가능
B는 한번만 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static char rc() throws IOException{
        sst.nextToken();
        return sst.sval.charAt(0);
    }

    static int ri() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static final int INF = 10000;

    public static void main(String[] args) throws IOException{
        N = ri();
        M = ri();

        // coin[i] = {val, A/B} A=0, B=1
        int[][] coin = new int[N][2];

        // M원을 거슬러 줄 때 최소한의 동전 갯수
        int[] dp = new int[M+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i<N; i++){
            int type = 0;
            if(rc() == 'B')
                type = 1;
            int val = ri();
            coin[i] = new int[]{val, type};
        }

        for(int i =0; i<N; i++){
            int cv = coin[i][0];
            int ct = coin[i][1];
            // 하나씩만 하는건 뒤에서부터 0-1 냅색
            if(ct == 1){
                for(int j = M; j>=cv; j--){
                    dp[j] = Math.min(dp[j], dp[j - cv]+1);
                }
            }
            // 여러번 쓸수 있는건 앞에서부터 완전 냅색
            else{
                for(int j = cv; j<=M; j++){
                    dp[j] = Math.min(dp[j], dp[j-cv]+1);
                }
            }
        }
        System.out.print(dp[M] == INF ? -1 : dp[M]);
    }
}