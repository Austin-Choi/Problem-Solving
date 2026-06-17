import java.util.*;
import java.io.*;

/*
주어진 입력 간선을 w 오름차순으로 정렬해서 union 연산을 하게 되면
모든 정점이 연결된 MST가 나올거고 이때 마지막으로 연결한 가중치를 저장해서 sum에서 빼게 되면 
그게 답 아닐까,,


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;
    static int sum = 0;
    static int cnt = 0;

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

        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            li.add(new int[]{read(), read(), read()});
        }
        Collections.sort(li, (a,b)->{
            return a[2] - b[2];
        });

        for(int[] ll : li){
            int a = ll[0];
            int b = ll[1];
            int w = ll[2];
            union(a,b,w);
            if(cnt == N-1){
                sum -= w;
                break;
            }
        }
        System.out.print(sum);
    }
}