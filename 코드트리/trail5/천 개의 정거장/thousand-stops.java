import java.util.*;
import java.io.*;

/*
버스 노선 -> 간선
cost, 노선에 포함된 정점 갯수 m
노선 정점 1 ... 노선 정점 m
-> 노선 정점 임시 리스트에 저장하고 
i = [0,list.size()-2], j = [1,list.size()-1]이고 i<j 일때
g[u].add(new int[]{v, cost, j-i})
-> 버스는 노선 순서대로 간다했으니 방향성있는 그래프임

근데 그래프 만드니까 터지는데?
-------------
그래프 정점 -> 갈수있는 정점 방식 말고 
정점 -> 이 정점에서 탈 수 있는 버스가 뭔지를 저장하고 간선은 다익스트라 중에 생성..
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int A,B,N;
    static ArrayList<Integer>[] bus;
    // bus i cost list
    static int[] cli;
    static ArrayList<int[]>[] g;
    static final long INF = 1_000_000_000L * 1_000 + 1;

    // 일단 최소비용 배열 B->A 방향으로 구하고
    // 그걸로 최소비용 DAG 만들어서 거기서 B->A 최소시간 구하기
    // -> 그냥 pq 기준 2개 넣어서 구하기 
    static long[] dijkstra(){
        long[] cost = new long[1001];
        long[] time = new long[1001];
        Arrays.fill(time, INF);
        Arrays.fill(cost, INF);
        cost[A] = 0;
        time[A] = 0;
        PriorityQueue<long[]> q = new PriorityQueue<>((a,b)->{
            if(a[1] != b[1])
                return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });
        // next, cost, time
        q.add(new long[]{A,0,0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cc = cur[1];
            long ct = cur[2];

            if(cost[ci] != cc)
                continue;
            if(time[ci] != ct)
                continue;

            for(int[] n : g[ci]){
                int bi = n[0];
                int bpos = n[1];

                for(int np = bpos+1; np < bus[bi].size(); np++){
                    int ni = bus[bi].get(np);
                    long nc = cli[bi];
                    int nt = np-bpos;

                    if(cost[ni] > cost[ci] + nc){
                        cost[ni] = cost[ci] + nc;
                        time[ni] = time[ci] + nt;
                        q.add(new long[]{(long) ni, cost[ni], time[ni]});
                    }
                    else if(cost[ni] == cost[ci] + nc && time[ni] > time[ci] + nt){
                        time[ni] = time[ci] + nt;
                        q.add(new long[]{(long) ni, cost[ni], time[ni]});
                    }
                }
            }
        }

        if(cost[B] == INF)
            return new long[]{-1,-1};
        return new long[]{cost[B], time[B]};
    }

    public static void main(String[] args) throws IOException{
        A = read();
        B = read();
        N = read();
        cli = new int[N];
        bus = new ArrayList[N];
        for(int i = 0; i<N; i++)
            bus[i] = new ArrayList<>();
        
        for(int i = 0; i<N; i++){
            cli[i] = read();
            int cnt = read();
            for(int j = 0; j<cnt; j++){
                int cur = read();
                bus[i].add(cur);
            }
        }

        g = new ArrayList[1001];
        for(int i = 1; i<= 1000; i++){
            g[i] = new ArrayList<>();
        }

        for(int n = 0; n<N; n++){
            for(int i = 0; i<bus[n].size(); i++){
                g[bus[n].get(i)].add(new int[]{n, i});
            }
        }
        long[] ans = dijkstra();
        System.out.print(ans[0]+" "+ans[1]);
    }
}