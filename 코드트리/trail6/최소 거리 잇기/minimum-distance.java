import java.util.*;
import java.io.*;

/*
dist long으로 놓고 제곱한거 그대로 쓰고 
간선 정보 저장하는 클래스 Info -> x, y, dist;
원래 주어진 간선은 따로 set에 long key로 저장해서 
모든 간선에 대해 간선정보 리스트 만들때 
-> longkey 할 필요 없이 먼저 주어진 간선으로 union 실행함.
대신 가중치 sum에는 0으로 기여함. 그래야 mst에 포함되더라도 이미 있었던 것이기 때문에 추가할 가중치에 영향 안줌
-> 그리고 가중치도 더할때마다 sqrt해야함
*/

public class Main {
    static class Info implements Comparable<Info>{
        int x;
        int y;
        long w;

        Info(int x, int y, long w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Info o){
            return Long.compare(this.w, o.w);
        }
    }
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[][] pos;
    static int[] parent;
    static int cnt = 0;
    static double sum = 0;

    static long getDist(int x1, int y1, int x2, int y2){
        long a = (long) (x2-x1) * (x2-x1);
        long b = (long) (y2-y1) * (y2-y1);
        return a+b;
    }

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(Info in){
        int a = in.x;
        int b = in.y;
        long w = in.w;

        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        sum += Math.sqrt(w);
        parent[pb] = pa;
        cnt++;
    }

    // static long posToKey(int a, int b){
    //     if(b < a){
    //         int t = a;
    //         a = b;
    //         b = t;
    //     }
    //     return (((long)a<<32) | b);
    // }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        pos = new int[N+1][2];
        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            pos[i][0] = read();
            pos[i][1] = read();
            parent[i] = i;
        }

        // Set<Long> ss = new HashSet<>();
        while(M-->0){
            int a = read();
            int b = read();
            // ss.add(posToKey(a,b));

            int pa = find(a);
            int pb = find(b);
            if(pa != pb){
                parent[pb] = pa;
                cnt++;
            }
        }

        ArrayList<Info> li = new ArrayList<>();
        for(int i = 1; i<=N; i++){
            for(int j = i+1; j<=N; j++){
                li.add(new Info(i,j,getDist(pos[i][0],pos[i][1],pos[j][0],pos[j][1])));
            }
        }

        Collections.sort(li);
        for(Info ll : li){
            if(cnt == N-1)
                break;
            union(ll);
        }
        System.out.printf("%.2f", sum);
    }
}