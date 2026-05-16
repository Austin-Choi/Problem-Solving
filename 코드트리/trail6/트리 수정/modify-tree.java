import java.util.*;
import java.io.*;

/*
그냥 트리지름 구하라는거 아닌가;
트리 지름 최대로 하려면 
트리지름 양끝 노드 구해가지고 그 사이에 포함되는 간선들이 그 제거/추가 후보가 되는데 

아 어디에다가 붙인다는 이야기는 없으니까 더 길게 도 가능할듯 
양끝 노드랑 그 경로 구해가지고 거기 포함 안되는 간선중에 제일 큰거 더하기 
만약 트리 지름 경로에 다 들어가있으면 트리 지름 그대로 출력하기
-> wa..

새로운 트리의 지름
컴포 1 내부 지름, 컴포 2 내부 지름, 새로운 간선을 지나는 최장 경로 중 max
-> 모든 가능한 제거 간선에 대해 diam(u) + diam(v) + w

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    // (int) 노드 번호, (long) weight
    static ArrayList<int[]>[] g;

    static long maxDist = 0;
    // i에서 자식 방향으로 가장 깊은 최대 거리
    static long[] down;
    // 부모 방향에서 내려오는 최대 거리
    static long[] up;
    // i번 노드의 subtree 내부 지름
    static long[] diam;
    // i번 노드의 subtree를 제외한 나머지 트리의 지름
    static long[] outDiam;

    static long ans = 0;

    static void dfs1(int ci, int parent){
        long top1 = 0;
        long top2 = 0;

        diam[ci] = 0;

        for(int[] n : g[ci]){
            if(n[0] == parent)
                continue;
            dfs1(n[0], ci);

            long tot = down[n[0]] + n[1];

            if(tot > top1){
                top2 = top1;
                top1 = tot;
            }
            else if(tot > top2){
                top2 = tot;
            }

            diam[ci] = Math.max(diam[ci], diam[n[0]]);
        }
        down[ci] = top1;
        diam[ci] = Math.max(diam[ci], top1 + top2);
    }

    // rerooting DP
    // 각 u-v 끊었을때 두 서브트리를 다시 연결했을때 가능한 최댓값 갱신
    static void dfs2(int ci, int parent){
        int m = g[ci].size();

        // val[i] = ci에서 i번째 자식으로 내려가는 최대 거리
        long[] val = new long[m];
        int[] nodes = new int[m];
        // prefix, suffix
        long[] prefix = new long[m+1];
        long[] suffix = new long[m+1];

        for(int i = 0; i<m; i++){
            nodes[i] = g[ci].get(i)[0];
            int cw = g[ci].get(i)[1];

            if(nodes[i] == parent)
                val[i] = -1;
            else
                val[i] = down[nodes[i]] + cw;
        }

        // 특정 child 하나를 제외하고 나머지 중 최대 down 값 구하기
        for(int i = 0; i<m; i++)
            prefix[i+1] = Math.max(prefix[i], val[i]);

        for(int i = m-1; i>=0; i--)
            suffix[i] = Math.max(suffix[i+1], val[i]);


        // 각 ni 기준으로 간선 끊기
        for(int i = 0;  i<m; i++){
            int ni = nodes[i];
            int cw = g[ci].get(i)[1];

            if(ni == parent)
                continue;
            
            // ni 제외한 U의 지름 계싼
            long top1 = 0;
            long top2 = 0;
            long maxSib = 0;
            
            // up 방향 arm (root면 0)
            long upArm = up[ci];
            if(upArm > top1){
                top2 = top1;
                top1 = upArm;
            }
            else if(upArm > top2){
                top2 = upArm;
            }

            // 다른 자식 arm + 지름
            for(int j = 0; j<m; j++){
                if(nodes[j] == parent || nodes[j] == ni)
                    continue;
                long otherArm = val[j];
                if(otherArm > top1){
                    top2 = top1;
                    top1 = otherArm;
                }
                else if(otherArm > top2)
                    top2 = otherArm;
                maxSib = Math.max(maxSib, diam[nodes[j]]);
            }

            long withCi = top1 + top2;
            long diamU = Math.max(withCi, Math.max(maxSib, outDiam[ci]));

            // ni의 subtree 지름 
            long diamV = diam[ni];

            // 제거한 간선 ci-ni, cw에 대해 재연결후 가능한 max 지름
            ans = Math.max(ans, diamU + diamV + cw);
            //ans = Math.max(ans, diamV);

            // rerooting 전파
            long bestOther = Math.max(prefix[i], suffix[i+1]);
            long uSide = Math.max(up[ci], bestOther);
            up[ni] = uSide + cw;

            // 다른쪽 트리 지름 전파
            outDiam[ni] = diamU;

            dfs2(ni, ci);

        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N];
        for(int i= 0; i<N; i++)
            g[i] = new ArrayList<>();
        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            int used = 0;
            g[u].add(new int[]{v,w, used});
            g[v].add(new int[]{u,w, used});
        }

        down = new long[N];
        up = new long[N];
        diam = new long[N];
        outDiam = new long[N];

        dfs1(0,-1);
        ans = diam[0];
        dfs2(0,-1);

        System.out.print(ans);
    }
}