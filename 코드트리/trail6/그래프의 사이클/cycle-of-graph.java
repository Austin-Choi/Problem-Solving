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
    static int[] size;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    // pa, pb가 같은데 연결하면 사이클이 생기니까
    // 연결하지 않는 것.

    static int union(int a, int b, int i){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return i;
        if(size[pb] < size[pa]){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
        return 0;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        size = new int[N+1];
        for(int i= 1; i<=N; i++){
            parent[i] = i;
            size[i] = 1;
        }
        int i = 1;
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        while(M-->0){
            int a = read();
            int b = read();
            int rst = union(a,b,i++);
            if(rst != 0){
                sb.append(rst).append("\n");
                found = true;
                break;
            }
        }
        if(found)
            System.out.print(sb);
        else
            System.out.print("happy");
    }
}