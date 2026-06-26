import java.util.*;
import java.io.*;

/*
위상정렬 해서 결과 order에 저장해놓고
dp1[i] = 1->i 로 가는 최대 정점 방문 수 -> 정방향 위상순서
dp2[i] = i->N 로 가는 최대 정점 방문 수 -> 역방향 위상순서 
어떤 정점 k 가 최대 정점 경로에 존재하려면
dp1[k] + dp2[k] -1 = dp1[N] 을 만족해야 함 
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
    static ArrayList<Integer>[] rg;
    static final int INF = Integer.MIN_VALUE;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        rg = new ArrayList[N+1];
        for(int i =1 ; i<=N; i++){
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }
        
        while(M-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            rg[b].add(a);
            indeg[b]++;
        }

        ArrayList<Integer> order = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1 ;i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            int ci = q.poll();
            order.add(ci);

            for(int n : g[ci]){
                if(--indeg[n]==0)
                    q.add(n);
            }
        }

        int[] dp1 = new int[N+1];
        dp1[1] = 1;
        for(int x : order){
            // 도달 못하는 정점은 못 감
            if(dp1[x] == 0)
                continue;

            for(int n : g[x]){
                dp1[n] = Math.max(dp1[n], dp1[x] + 1);
            }
        }

        Collections.reverse(order);
        // 역그래프 필요함
        int[] dp2 = new int[N+1];
        dp2[N] = 1;
        for(int x : order){
            if(dp2[x] == 0)
                continue;

            for(int n : rg[x]){
                dp2[n] = Math.max(dp2[n], dp2[x] + 1);
            }
        }

        if(dp1[N] == 0){
            System.out.print(-1);
            return;
        }

        int cur = 1;
        ans.add(1);

        while(cur != N){
            int min = Integer.MAX_VALUE;
            for(int n : g[cur]){
                // 정방향 위상 순서에서 그다음 정점이어야 하고
                if(dp1[n] != dp1[cur] + 1)
                    continue;
                // 최장경로 위 정점 조건 만족해야함
                if(dp1[n] + dp2[n] - 1 != dp1[N])
                    continue;
                
                min = Math.min(min, n);
            }
            ans.add(min);
            cur = min;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp1[N]).append("\n");
        for(int x : ans){
            sb.append(x).append(" ");
        }
        System.out.print(sb);
    }
}