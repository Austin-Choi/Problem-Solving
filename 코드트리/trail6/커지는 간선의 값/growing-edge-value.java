import java.util.*;
import java.io.*;

/*
K씩 올라간다해도 정렬순서는 똑같음
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static ArrayList<int[]>[] g;

    static long prim(){
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        q.add(new int[]{1,0});
        boolean[] v = new boolean[N+1];
        long sum = 0;
        int k = 0;
        boolean first = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(v[ci])
                continue;
            v[ci] = true;
            if(first){
                first = false;
            }
            else
                sum += (k++ * K) + cd;

            for(int[] n : g[ci]){
                if(v[n[0]])
                    continue;
                q.add(n);
            }
        }
        return sum;
    }


    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int u = read();
            int v=  read();
            int w= read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }
        System.out.print(prim());
    }
}