import java.util.*;
import java.io.*;
/*
dfs로 자식->부모 로 올라가면서 갱신
arrayList로 그래프 양방향 구성하고
임의의 노드 root로 잡기

자식 -> 부모로 해야하는데 이건 dfs로 post-order로 짜주면 됨
1) cur에서 start
2) 자식들 먼저 dfs호출
3) dfs (next, cur) 끝나고 dp[cur][상태] 계산
 */
public class Main {
    static int N;
    static ArrayList<Integer>[] g;
    static int[] ppl;
    static int[][] dp;

    static void dfs(int cur, int p){
        dp[cur][1] = ppl[cur];

        for(int n : g[cur]){
            if(n == p)
                continue;

            dfs(n, cur);
            dp[cur][0] += Math.max(dp[n][0], dp[n][1]);
            dp[cur][1] += dp[n][0];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ppl = new int[N+1];
        for(int i = 1; i<=N; i++){
            ppl[i] = Integer.parseInt(st.nextToken());
        }
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        for(int i = 1; i<=N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        //dp[cur][2] = 선택안함[0], 선택함[1]
        dp = new int[N+1][2];
        dfs(1, -1);
        int ans = Math.max(dp[1][0], dp[1][1]);
        System.out.println(ans);
    }
}