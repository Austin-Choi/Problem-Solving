import java.util.*;
import java.io.*;

/*
전체 가중치 합 - mst 가중치 합
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int sum = 0;
    static int cnt = 0;
    static int[] parent;

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
        cnt++;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        for(int i = 1; i<=N; i++)
            parent[i] = i;

        int wSum = 0;
        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            li.add(new int[]{a,b,w});
            wSum += w;
        }

        Collections.sort(li, Comparator.comparingInt(a->a[2]));
        for(int[] ll : li){
            union(ll[0], ll[1], ll[2]);
            if(cnt == N-1)
                break;
        }
        System.out.print(wSum - sum);
    }
}