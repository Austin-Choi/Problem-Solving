import java.util.*;
import java.io.*;

/*
양방향 그래프라서 역그래프를 만들어도 원래 그래프와 같음
하나의 간선을 제거하는데 최단거리값을 바꾼다했으니 모든 최단경로에서 공유되는 간선을 찾아야함
1) 1->N dist랑 N->1 dist 구하기
2) dist 2개 구한걸로 정방향 dp로 cntS 구하기, 역방향 dp로 cntE 구하기
-> total = cntS[N];

cntS[ni] * cntE[ci] == total
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<int[]>[] g;
    static final int INF = 5_000 * 10_000 + 1; 

    static int[] dijkstra(int si){
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[si] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        q.add(new int[]{si, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];

                if(dist[ni] > dist[ci]+nd){
                    dist[ni] = dist[ci]+nd;
                    q.add(new int[]{ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
            g[i] = new ArrayList<>();
        
        while(M-->0){
            int s = read();
            int e = read();
            int w = read();
            g[s].add(new int[]{e,w});
            g[e].add(new int[]{s,w});
        }

        int[] distSE = dijkstra(1);
        int[] distES = dijkstra(N);

        int D = distSE[N];
        if(D == INF){
            System.out.println(0);
            return;
        }

        // 1에서 도달가능한 노드들
        List<Integer> nodes = new ArrayList<>();
        for(int i=1; i<=N; i++){
            if(distSE[i] < INF 
                && distES[i] < INF 
                && distSE[i] + distES[i] == D){
                nodes.add(i);
            }
        }

        long[] cntS = new long[N+1];
        long[] cntE = new long[N+1];
        cntS[1] = 1;
        cntE[N] = 1;

        // cntS: 오름차순 distSE
        List<Integer> ss = new ArrayList<>(nodes);
        Collections.sort(ss, Comparator.comparingInt(i -> distSE[i]));
        for(int ci : ss){
            if(cntS[ci] == 0) 
                continue;

            for(int[] n : g[ci]){
                int ni = n[0];
                int nw = n[1];
                if((distSE[ni] == distSE[ci] + nw) && (distSE[ci] + nw + distES[ni] == D)){
                    cntS[ni] += cntS[ci];
                }
            }
        }

        // 여기서 계속 뭔가 틀림!!!
        // cntE: 역방향이라 내림차순으로 해야함
        Collections.sort(ss, Comparator.comparingInt(i -> -distSE[i]));
        for(int ci : ss){
            if(cntE[ci] == 0) 
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nw = n[1];
                if((distSE[ci] == distSE[ni] + nw) && (distSE[ni] + nw + distES[ci] == D)){ 
                    cntE[ni] += cntE[ci];
                }
            }
        }

        long total = cntS[N];
        if(total == 0){
            System.out.println(0);
            return;
        }

        int ans = 0;
        for(int ci=1; ci<=N; ci++){
            for(int[] n : g[ci]){
                int ni = n[0];
                int w = n[1];
                if(ci < ni){ 
                    // ci -> ni 방향으로 사용되는거랑 ni->ci 방향으로 사용되는거 둘다 포함해서 ㅂ
                    if(distSE[ci] + w + distES[ni] == D 
                        || distSE[ni] + w + distES[ci] == D){
                        boolean critical = false;
                        if(distSE[ci] + w == distSE[ni]){
                            if(cntS[ci] * cntE[ni] == total){
                                critical = true;
                            }
                        }
                        if(distSE[ni] + w == distSE[ci]){
                            if(cntS[ni] * cntE[ci] == total){
                                critical = true;
                            }
                        }
                        if(critical)
                            ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}