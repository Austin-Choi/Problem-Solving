/*
아무 음의 사이클 존재하면 -1
-> 거리 갱신이 비정상적으로 많이 일어난다
-> N-1개가 필요한데 N번째에도 갱신됨
1번에서 2,3,,,N번까지의 최단경로 출력
INF면 -1

int형 오버플로 이슈, inQ 초기화 문제
cnt도 갱신될때만 ++

휴리스틱 최적화 -> spfa
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static ArrayList<int[]>[] g;
    static final long INF = Long.MAX_VALUE;
    // 큐애는 정점만
    static long[] spfa(){
        // 최단 경로는 N번째까지 갱신되면 안됨
        int[] cnt = new int[N+1];
        // q에서 계속 갱신될 수 있는 중복 정점 방지
        boolean[] inQ = new boolean[N+1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        inQ[1] = true;
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        while(!q.isEmpty()){
            int cu = q.poll();
            inQ[cu] = false;

            for(int[] n : g[cu]){
                int ni = n[0];
                int nd = n[1];
                if(dist[cu] != INF && dist[ni] > dist[cu] + nd){
                    dist[ni] = dist[cu] + nd;

                    if(!inQ[ni]){
                        q.add(ni);
                        cnt[ni]++;
                        if(cnt[ni] >= N)
                            return new long[]{-1};
                        inQ[ni] = true;
                    }
                }
            }
        }

        return dist;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        // 같은 노선 여러개 입력 가능함;
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,d});
        }
        long[] rst = spfa();
        if(rst[0] == -1)
            System.out.print(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for(int i = 2; i<=N; i++){
                long a = rst[i];
                if(a == INF)
                    a = -1;
                sb.append(a);
                if(i <=N-1)
                    sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}
