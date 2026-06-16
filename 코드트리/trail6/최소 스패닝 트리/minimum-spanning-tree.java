import java.util.*;
import java.io.*;


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;
    static int sum = 0;

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
        sum += w;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        for(int i= 1; i<=N; i++)
            parent[i] = i;
        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            li.add(new int[]{read(), read(), read()});
        }
        Collections.sort(li, (a,b)->{
            return a[2] - b[2];
        });

        for(int[] l : li){
            int u = l[0];
            int v = l[1];
            int w = l[2];

            union(u,v,w);
        }
        System.out.print(sum);
    }
}