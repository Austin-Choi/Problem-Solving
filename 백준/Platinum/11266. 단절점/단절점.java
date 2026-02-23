/*
어떤 부모 정점 u가 있고 자식 정점 v가 있는데
v를 포함한 v의 서브트리가 u의 조상으로 u를 거쳐야만 갈 수 있으면 u가 단절점

low[u] = u의 서브트리 전체가 부모를 제외하고 조상으로 갈 수 있는 가장 빠른 방문 순서
-> 무방향이라 부모간선 항상 있음 -> 부모제외
-> dfs 시작에서 자기 자신은 방문 가능하니까 low[cur] = disc[cur]
disc[u] = 방문 순서

dfs에서 자식이 위로 도망갈수 있으면 low 내려감
내가 직접 조상으로 갈 수 있으면 low 내려감

cur의 모든 자식 정점에 대해
하나라도 low[자식] >= disc[본인]
이면 cur는 단절점

루트는 특수하게 자식이 2개 이상이면 무조건 단절점
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static ArrayList<Integer>[] g;
    static int[] disc;
    static int[] low;
    static int order = 0;
    static boolean[] isCut;

    // 루트는 0,-1로 들어옴
    static void dfs(int cur, int parent){
        disc[cur] = low[cur] = ++order;
        int child = 0;

        for(int next : g[cur]){
            if(next == parent)
                continue;

            // 방문 안한 자식 발견
            if(disc[next] == 0){
                child++;
                dfs(next, cur);

                low[cur] = Math.min(low[cur], low[next]);

                if(parent != -1 && low[next] >= disc[cur])
                    isCut[cur] = true;
            }
            // 자식이 이미 방문되어있음
            // 다시 돌아오는 back-edge 존재
            else{
                low[cur] = Math.min(low[cur], disc[next]);
            }
        }

        if(parent == -1 && child >= 2)
            isCut[cur] = true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        disc = new int[N+1];
        low = new int[N+1];
        isCut = new boolean[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        int root = -1;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            if(i == 0)
                root = u;
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        // visited는 공유되니까 모든 정점으로 해놔도 O(V+E)임
        for(int i = 1; i<=N; i++){
            if(disc[i] == 0)
                dfs(i, -1);
        }
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            if(isCut[i]){
                ans++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(ans);
        System.out.print(sb);
    }
}