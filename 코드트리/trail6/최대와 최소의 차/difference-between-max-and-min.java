import java.util.*;
import java.io.*;

/*
n r b
-> max : b 오름차순
-> min : r 오름차순
빨간색이 0으로 입력 들어옴
0 -> r=1 b=0, 1-> r=0 b=1
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;
    static int redCnt = 0;
    static int cnt = 0;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b, boolean isRed){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
        if(isRed)
            redCnt++;
        cnt++;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        for(int i = 1; i<=N; i++)
            parent[i] = i;
        // u, v, r, b
        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            int u = read();
            int v = read();
            int cmd = read();
            if(cmd == 0)
                li.add(new int[]{u,v,1,0});
            else
                li.add(new int[]{u,v,0,1});
        }

        //max
        Collections.sort(li, Comparator.comparingInt(a->-a[2]));
        for(int[] ll : li){
            int u = ll[0];
            int v = ll[1];
            int r = ll[2];

            union(u, v, r==1);
            if(cnt == N-1)
                break;
        }

        int max = redCnt * redCnt;
        // parent도 초기화해야함
        parent = new int[N+1];
        for(int i = 1; i<=N; i++)
            parent[i] = i;
        redCnt = 0;
        cnt = 0;

        Collections.sort(li, Comparator.comparingInt(a->a[2]));
        for(int[] ll : li){
            int u = ll[0];
            int v = ll[1];
            int r = ll[2];

            union(u,v,r==1);
            if(cnt == N-1)
                break;
        }
        int min = redCnt*redCnt;
        System.out.print(max - min);
    }
}