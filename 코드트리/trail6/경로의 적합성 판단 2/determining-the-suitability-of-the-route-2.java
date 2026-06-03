import java.util.*;
import java.io.*;

/*
입력받은거 union 해서 연결해주고 
순서대로 이동하는건 전부 같은 루트 노드를 가져야해서 
find(i-1, i)했는데 하나라도 루트 다르면 불가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int[] parent;
    static int[] size;

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

        if(size[pb] > size[pa]){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        parent = new int[N+1];
        size = new int[N+1];
        for(int i= 1; i<=N; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(M-->0){
            int u = read();
            int v = read();
            union(u,v);
        }

        int prev = read();
        // 정점 1개면 무조건 true라서 true로 초기화
        boolean ans = true;
        for(int i = 1; i<K; i++){
            int cur = read();
            if(find(prev) == find(cur)){
                ans = true;
                prev = cur;
            }
            else{
                ans = false;
                break;
            }
        }
        
        System.out.print(ans? 1 : 0);
    }
}