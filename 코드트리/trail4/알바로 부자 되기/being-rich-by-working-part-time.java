import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        List<int[]> in = new ArrayList<>();
        for(int i = 0; i<N; i++){
            in.add(new int[]{read(), read(), read()});
        }
        Collections.sort(in, (a,b)->{
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int ans = 0;
        // i번 알바에서 시작했을때 최대로 버는 금액
        int[] dp = new int[N];

        for(int i = 0; i<N; i++){
            dp[i] = in.get(i)[2];
            int curStart = in.get(i)[0];

            for(int j = 0; j<i; j++){
                int prevEnd = in.get(j)[1];

                if(curStart > prevEnd){
                    dp[i] = Math.max(dp[i], dp[j]+in.get(i)[2]);
                }
            }

            ans = Math.max(ans, dp[i]);
        }
        System.out.print(ans);
    }
}