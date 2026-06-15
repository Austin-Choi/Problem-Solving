import java.util.*;
import java.io.*;

/*가장 작은 값이 최대가 되려면 A-B로 가는 간선중에서 간선을 만족도 큰 순으로 정렬해서 
MST 만들고 트리에서 특정 지점으로 가는 경로 유일하니까 dfs로 따라가면서 제일 작은 값 추적?

  4-5
1-4-3-6
1-2  

사실 dfs도 필요없이 union하고 나서 내림차순 정렬이니까 pa == pb일때 w가 정답임
-> 그래프 만들 필요도 없음.
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,A,B;
    static int[] parent;
    static ArrayList<int[]> li = new ArrayList<>();
    static ArrayList<int[]>[] g;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b, int w){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
        //g[a].add(new int[]{b,w});
        //g[b].add(new int[]{a,w});
    }

    static final int INF = 1000000001;
    static int ans = INF;
    static void dfs(int cur, int dest, int min, int p){
        if(cur == dest){
            ans = min;
            return;
        }

        for(int[] v : g[cur]){
            int ni = v[0];
            int nw = v[1];
            if(p == ni)
                continue;
            int nm = Math.min(min, nw);
            dfs(ni, dest, nm, cur);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        A = read();
        B = read();
        //g = new ArrayList[N+1];
        parent = new int[N+1];

        for(int i = 1; i<=N; i++){
            //g[i] = new ArrayList<>();
            parent[i] = i;
        }

        while(M-->0)
            li.add(new int[]{read(), read(), read()});
        Collections.sort(li, Comparator.comparingInt(a->-a[2]));

        for(int[] v : li){
            int s = v[0];
            int e = v[1];
            int w = v[2];

            union(s,e,w);
            if(find(A) == find(B)){
                ans = w;
                break;
            }
        }

        //dfs(A,B,INF,0);
        System.out.print(ans);
    }
}