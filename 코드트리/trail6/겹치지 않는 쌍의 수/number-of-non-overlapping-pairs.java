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
        long[] g = new long[N+1];
        for(int i = 1; i<=N; i++){
            int cnt = read();
            for(int j = 0; j<cnt; j++){
                int cur = read();
                g[i] |= (1L<<cur);
            }
        }

        int ans = 0;
        for(int i= 1; i<=N-1; i++){
            for(int j = i+1; j<=N; j++){
                if((g[i] & g[j]) == 0)
                    ans++;
            }
        }
        System.out.print(ans);
    }
}