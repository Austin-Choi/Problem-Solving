import java.util.*;
import java.io.*;

/*
합이 가능한지 보고 
하나가 결정되면 나머지 하나는 자동으로 결정되고 
-> 부분집합 하나만 결정하면 나머지는 보완집합이 됨 (complement)
bool dp[i] = 합이 i인게 가능한지
정확히 2개의 그룹으로 나눴는데 A와 B의 합이 일치한다는건 
tot / 2 가 가능한지만 알면 된다는 뜻

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
        int tot = 0;
        for(int i =0 ; i<N; i++){
            A[i] = read();
            tot += A[i];
        }

        if(tot % 2 != 0){
            System.out.print("No");
            return;
        }

        boolean[] dp = new boolean[tot+1];
        dp[0] = true;

        for(int i = 0;  i<N; i++){
            int cur = A[i];
            for(int j = tot; j>=cur; j--){
                dp[j] |= dp[j-cur];
            }
        }

        System.out.print(dp[tot/2] ? "Yes" : "No");
    }
}