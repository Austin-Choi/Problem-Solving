import java.util.*;
import java.io.*;


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] parent, size;

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
        size[pa] += size[pb];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        parent = new int[100000+1];
        size = new int[100000+1];
        for(int i = 1; i<=100000; i++){
            parent[i] = i;
            size[i] = 1;
        }
        
        StringBuilder sb = new StringBuilder();
        while(N-->0){
            int a = read();
            int b = read();
            union(a, b);
            sb.append(size[find(a)]).append("\n");
        }
        System.out.print(sb);
    }
}