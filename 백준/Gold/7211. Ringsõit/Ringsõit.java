/*
크루스칼로 mst 구성
-> 클래스 만들고 가중치 적은순으로 정렬하고
union find로
 */
import java.util.*;
import java.io.*;
public class Main {
    static class Edge{
        int u, v;
        long w;
        int type;
        Edge(int u, int v, long w, int type){
            this.u = u;
            this.v = v;
            this.w = w;
            this.type =type;
        }
    }
    static int N,M,K;
    static ArrayList<Edge> g;
    static long total = 0L;
    static long buymoney = 0L;
    static long nationmoney = 0L;
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py)
            return;
        parent[py] = px;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        g = new ArrayList<>();
        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            g.add(new Edge(u,v,w,1));
            total += w;
        }
        while(K-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g.add(new Edge(u,v,w,0));
        }
        Collections.sort(g, (a,b)->{
            if(a.w != b.w)
                return Long.compare(a.w, b.w);
            // 국가먼저(1이라 더커서 반대로)
            return b.type - a.type;
        });

        for(Edge e : g){
            if(find(e.u) != find(e.v)) {
                union(e.u, e.v);

                if(e.type == 1){
                    nationmoney += e.w;
                }
                else
                    buymoney += e.w;
            }
        }
        total -= nationmoney;
        System.out.println(total >= buymoney ? 0 : buymoney-total);
    }
}
