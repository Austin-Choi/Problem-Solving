/*
음수 간선 있으니까 벨만포드 or SPFA
spfa에서 dist는 작은쪽으로 갱신하는 용도임
그런데 음수 사이클이 없어도 같은 정점이 여러번 갱신될 수 잇음
inQ로 종료를 보장함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static ArrayList<int[]>[] g;
    static final long INF = Long.MAX_VALUE;
    static StringBuilder spfa(){
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] inQ = new boolean[N+1];
        q.add(1);
        dist[1] = 0;
        inQ[1] = true;

        while(!q.isEmpty()){
            int ci = q.poll();
            inQ[ci] = false;

            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];

                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci] + nd;
                    if(!inQ[ni]){
                        inQ[ni] = true;
                        q.add(ni);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i<=N; i++)
            if(dist[i] < 0)
                sb.append(i).append("\n");
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            boolean isPositive = st.nextToken().equals("b");
            int n = Integer.parseInt(st.nextToken());
            if(!isPositive)
                n = -n;
            g[u].add(new int[]{v,n});
        }
        System.out.print(spfa());
    }
}
