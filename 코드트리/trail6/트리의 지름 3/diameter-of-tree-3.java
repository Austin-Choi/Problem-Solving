import java.util.*;
import java.io.*;

/*
트리 DP?
상위 3개 관리하면서 그 3개로부터 나올수 있는 조합
a>=b>=c
a+b, a+c, b+c를 리스트 안에 넣고 dfs자체는 top1 만 리턴
-> 이렇게하니까 WA

일단 가장 긴 트리의 지름을 구하고 양 끝점을 구하기
-> 양 끝점에서 출발하는 모든 경로들은 트리의 가징 긴 지름들의 후보 경로니까 
트리의 지름에 해당하는애들만 제외하고 구해보기???

그리고 하나의 끝점 잡고 다른 끝점 제외하고 제일 긴거 구하고
다른끝점도 똑같이 하고 
그 두개 긴거중에 max값 구하면 그게 이 트리에서 2번째로 긴 지름
-> 둘이 2등 3등이니까
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<int[]>[] g;
    
    static int p1;
    static int p2;
    static long maxDist = -1;
    
    static void dfs(int ci, long dist, int parent, boolean isFirst){
        if(dist > maxDist){
            maxDist = dist;
            if(isFirst)
                p1 = ci;
            else
                p2 = ci;
        }

        for(int[] n : g[ci]){
            if(n[0] == parent)
                continue;
            dfs(n[0], dist+n[1], ci, isFirst);
        }
    }

    // 트리의 각 끝점 잡고 모든 정점에 대해서 경로 구하기
    // no는 피해야할 다른 끝점임
    static long bfs(int si, int no){
        long[] dist = new long[N+1];
        Arrays.fill(dist, -1);
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(si);
        visited[si] = true;
        dist[si] = 0;

        while(!q.isEmpty()){
            int ci = q.poll();
            for(int[] n : g[ci]){
                if(n[0] == no)
                    continue;
                if(visited[n[0]])
                    continue;
                visited[n[0]] = true;
                dist[n[0]] = dist[ci] + n[1];
                q.add(n[0]);
            }
        }

        long rst = 0;
        for(long d : dist){
            rst = Math.max(rst, d);
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();

            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }        

        dfs(1, 0, -1, true);

        maxDist = -1;
        dfs(p1, 0, -1, false);

        long d1 = bfs(p1, p2);
        long d2 = bfs(p2, p1);
        System.out.print(Math.max(d1,d2));
    }
}