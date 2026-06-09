import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] parent;
    static int[] size;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static SortedSet<Integer> ss = new TreeSet<>();
    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        if(pb < pa){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        parent = new int[N+1];
        size = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
            size[i] = 1;
        }
        int M = N-2;
        while(M-->0){
            union(read(), read());
        }
        for(int i = 1; i<=N; i++){
            ss.add(find(i));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = ss.iterator();
        int cnt = 0;
        while(it.hasNext()){
            if(cnt == 2)
                break;
            sb.append(it.next()).append(" ");
            cnt++;
        }
        System.out.print(sb);
    }
}