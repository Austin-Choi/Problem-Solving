import java.util.*;
import java.io.*;

/*
정점 수가 많아서 간선을 전부 관리하기 힘들땐 프림을 써야함
현재 활성화된 정점 기준 나가는 간선만 보기 때문임.
좌표축 기준 정렬을 써서 압축한다면 모를까 그것마저도 어려운 조건이 있기 때문

prim - 인접리스트 입력 후 현재 정점기준 다음 후보중 유력한거 선택
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<int[]>[] g;
    static boolean[] v;

    static long prim(int s){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        pq.add(new int[]{s,0});
        long sum = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(v[ci])
                continue;
            v[ci] = true;
            sum += cd;

            for(int[] n : g[ci]){
                pq.add(n);
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        v = new boolean[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }
        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            g[a].add(new int[]{b,w});
            g[b].add(new int[]{a,w});
        }

        long ans = 0;
        for(int i=1;i<=N;i++){
            if(!v[i])
                ans += prim(i);
        }
        System.out.print(ans);
    }
}