import java.util.*;
import java.io.*;

/*
입력 정리
7
N = 7
5 0
1 1 1
1번 작업 끝내는데 시간 5, 0개 선행작업
2번 작업 끝내는데 시간 1 1개 선행작업 1번
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] indeg;
    // 다음 정점, 가중치
    static ArrayList<Integer>[] g;
    static int[] time;

    static long topoSort(){
        Queue<Integer> q = new ArrayDeque<>();
        long[] dp = new long[N+1];
        for(int i =1 ; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);
                dp[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                dp[n] = Math.max(dp[n], dp[ci] + time[n]);
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        long max = -1;
        for(long l : dp){
            max = Math.max(max, l);
        }
        return max;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        indeg = new int[N+1];
        time = new int[N+1];
        g = new ArrayList[N+1];
        for(int i =1 ;i<=N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 1; i<=N; i++){
            time[i] = read();
            int t = read();
            for(int k = 0; k<t; k++){
                int v = read();
                g[v].add(i);
                indeg[i]++;
            }
        }
        System.out.print(topoSort());
    }
}