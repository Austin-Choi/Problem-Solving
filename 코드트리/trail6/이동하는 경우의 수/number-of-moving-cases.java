import java.util.*;
import java.io.*;

/*
위상정렬에서 큐에서 정점을 꺼냈다는 것은 이미 정점 처리를 완료했다는거니까
DP를 동시에 수행할 수 있음.
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] indeg;
    static ArrayList<int[]>[] g;
    static ArrayList<int[]>[] rg;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        rg = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            g[a].add(new int[]{b,w});
            indeg[b]++;
            rg[b].add(new int[]{a,w});
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }
        int[] dp1 = new int[N+1];
        Arrays.fill(dp1, Integer.MIN_VALUE);
        dp1[1] = 0;

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int[] n : g[ci]){
                int ni = n[0];
                int nw = n[1];

                if(dp1[ci] != Integer.MIN_VALUE 
                    && dp1[ni] < dp1[ci] + nw){
                        dp1[ni] = dp1[ci] + nw;

                    }
                if(--indeg[ni] == 0){
                    q.add(ni);
                }
            }
        }

        boolean[] v = new boolean[N+1];
        int cnt = 0;
        q = new ArrayDeque<>();
        q.add(N);
        v[N] = true;

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int[] n : rg[ci]){
                int ni = n[0];
                int nw = n[1];

                if(dp1[ci] == dp1[ni] + nw){
                    cnt++;
                    if(v[ni])
                        continue;
                    v[ni] = true;
                    q.add(ni);
                }
            }
        }

        StringBuilder sb= new StringBuilder();
        sb.append(dp1[N]).append(" ").append(cnt);
        System.out.print(sb);
    }
}