import java.util.*;
import java.io.*;

/*
아무 점이나 시작해서 크루스칼 돌린다음에 마지막 방문 정점에서 dfs돌리고 얻은 정점에서 dfs돌려서 트리의 지름 구하기?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<int[]>[] g;
    static ArrayList<int[]>[] t;
    static long maxDist = 0L;
    static int far = -1;

    static void dfs(int cur, int p, long dist){
        if(dist > maxDist){
            maxDist = dist;
            far = cur;
        }
        for(int[] n : t[cur]){
            int ni = n[0];
            int nd = n[1];
            if(ni == p)
                continue;
            dfs(ni, cur, dist + nd);
        }
    }

    static long prim(){
        boolean[] v = new boolean[N+1];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        q.add(new int[]{0,1,0});
        long sum = 0;
        boolean first = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int s = cur[0];
            int ci = cur[1];
            int cd = cur[2];

            if(v[ci])
                continue;
            v[ci] = true;
            sum += cd;
            if(first)
                first = false;
            else{
                t[s].add(new int[]{ci,cd});
                t[ci].add(new int[]{s,cd});
            }
            

            for(int[] n : g[ci]){
                if(v[n[1]])
                    continue;
                q.add(n);
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        t = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
            t[i] = new ArrayList<>();
        }

        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            g[a].add(new int[]{a,b,w});
            g[b].add(new int[]{b,a,w});
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prim()).append("\n");
        dfs(1,0,0);
        dfs(far,0,0);
        sb.append(maxDist);
        System.out.print(sb);
    }
}