import java.util.*;
import java.io.*;

/*
색칠되지 않은 모든 정점들에 대해 최소 하나는 연결되게 MST 만들기
-> 전부 연결하는건 아니므로 색칠정점의 우선순위가 높은건 아닌데, 가중치 오름차순 정렬해야할거같고
크루스칼하는데 색칠된 정점 서로 포함하면 굳이 안합쳐도 됨
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int[] parent;
    static boolean[] hasColor;
    static int cnt = 0;
    static long sum = 0;

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
        if(hasColor[pa] == true && hasColor[pb] == true)
            return;
        parent[pb] = pa;
        hasColor[pa] |= hasColor[pb];
        cnt++;
        sum+=w;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        hasColor = new boolean[N+1];
        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }
        while(K-->0){
            hasColor[read()] = true;
        }
        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            li.add(new int[]{a,b,w});
        }
        Collections.sort(li, Comparator.comparingInt(a->a[2]));
        for(int[] l : li){
            if(cnt == N-1)
                break;
            union(l[0], l[1], l[2]);
        }
        System.out.print(sum);
    }
}