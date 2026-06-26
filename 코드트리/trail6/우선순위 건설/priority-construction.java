import java.util.*;
import java.io.*;


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] indeg;
    static int[] time;
    static ArrayList<Integer>[] g;

    public static void main(String[] args) throws IOException{
        N = read();
        time = new int[N+1];
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 1; i<=N; i++){
            time[i] = read();
            while(true){
                int cur = read();
                if(cur == -1)
                    break;
                g[cur].add(i);
                indeg[i]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        // 동시에 지을 수 있다는 것은 최대값을 구하라는 것
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);
                dp[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                if(dp[ci] != Integer.MIN_VALUE 
                    && dp[n] < dp[ci] + time[n]){
                    dp[n] = dp[ci] + time[n];
                }
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            sb.append(dp[i]).append("\n");
        }
        System.out.print(sb);
    }
}