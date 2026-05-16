import java.util.*;
import java.io.*;

/*
visited 전역으로 쓰면서 모든 정점에 대해 아직 방문처리 안된거 하나 잡고 dfs 쭉돌려서 해당 컴포넌트 전체 정점 갯수 셈
-> visited int로 잡아서 한번 돌릴때마다 vNum++해줌
-> 초기화는 -1로 해서 -1이면 미방문임 
-> dfs 안쪽에서는 해당 vNum 아니거나 -1이면 미방문 처리

-> 생각해보면 정점 갯수 셀필요도 없음 vnum쓰잖아.

사이클 발생하거나 (간선 카운팅 세서 N-1 넘어가서 갱신되면 사이클)
경로가 유일하지 않거나 (그냥 위에 조건으로 필터링될듯?)
-> 아 vNum 쓰니까 vNum나오면 사이클이지.

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<Integer>[] g;
    static int[] v;
    static int vNum = 0;
    static boolean hasCycle;

    static void dfs(int cur, int parent, int cv){
        v[cur] = cv;

        for(int n : g[cur]){
            if(n == parent)
                continue;
            if(v[n] == cv)
                hasCycle = true;
            else if(v[n] == -1)
                dfs(n, cur, cv);
        }
    }


    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        v = new int[N+1];
        Arrays.fill(v,-1);

        while(M-->0){
            int u = read();
            int vv = read();
            g[u].add(vv);
            g[vv].add(u);
        }

        int ans = 0;
        for(int i = 1; i<=N; i++){
            if(v[i] == -1){
                hasCycle = false;
                dfs(i,-1,vNum++);
                if(!hasCycle)
                    ans++;
            }
        }
        System.out.print(ans);
    }
}