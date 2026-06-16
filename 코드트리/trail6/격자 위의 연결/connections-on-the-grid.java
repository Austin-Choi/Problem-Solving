import java.util.*;
import java.io.*;

/*
- - -

1 2 3
4 5 6
7 8 9
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;
    static int cnt = 0;
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
        cnt++;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N*M+1];
        for(int i = 1; i<=N*M; i++)
            parent[i] = i;
        
        ArrayList<int[]> li = new ArrayList<>();
        for(int i= 0; i<N; i++){
            for(int j = 1; j<M; j++){
                int u = M*i + j-1 +1;
                int v = M*i + j +1;
                int w = read();
                li.add(new int[]{u,v,w});
            }
        }

        for(int i = 1; i<N; i++){
            for(int j= 0; j<M; j++){
                int u = M*(i-1) + j + 1;
                int v = M*i + j + 1;
                int w = read();
                li.add(new int[]{u,v,w});
            }
        }

        Collections.sort(li, (a,b)->{
            return a[2] - b[2];
        });

        for(int[] ll : li){
            if(cnt == (M*N-1)){
                break;
            }
            int u = ll[0];
            int v = ll[1];
            int w = ll[2];
            union(u,v,w);
        }
        System.out.print(sum);
    }
}