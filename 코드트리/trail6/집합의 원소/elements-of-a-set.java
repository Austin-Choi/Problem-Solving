import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static int[] parent;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }

        while(M-->0){
            int type = read();
            if(type == 0)
                union(read(), read());
            else{
                int a = read();
                int b = read();
                if(find(a) == find(b))
                    System.out.println(1);
                else
                    System.out.println(0);
            }
        }
    }
}