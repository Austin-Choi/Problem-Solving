import java.util.*;
import java.io.*;

/*
서로 다른 경로의 수라하면 dp[n] = dp[n] + dp[ci]
초기값은 dp[i] = 1
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] g;
    static final int MOD = 100_000_007;

    static int topoSort(){
        int[] dp = new int[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i =1 ; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        // dp 시작점과 위상정렬 시작점은 별개
        dp[1] = 1;

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                dp[n] = (dp[n] + dp[ci]) % MOD;
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        while(M-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            indeg[b]++;
        }
        System.out.print(topoSort());
    }
}