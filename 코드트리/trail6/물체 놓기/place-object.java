import java.util.*;
import java.io.*;

/*
단방향 간선이라는데 그러면 i->j랑 j->i 값이 다를수 있다는걸 염두해두고 풀어야한다는건가?
모든 정점에 물체를 놓는 최소 비용이라 하면 
이미 물체가 존재하는 정점에서 연결만 하면 해당 정점 놓는 물체 비용이 0이 됨
-> 가장 적은 직접물체 비용 정점에서 시작해서 최소 가중치 cost로 MST 구성하기
3
4->1->2->3
0 2 4 7

-----------
반례 : 직접 설치를 여러번 하는게 더 최적일 수 있음
-> 가상 정점을 만들어서 설치도 가중치로 넣기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<int[]>[] g;

    static long prim(){
        int s= 0;
        boolean[] v = new boolean[N+1];
        // ni, cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        pq.add(new int[]{s,0});
        long sum = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(v[ci])
                continue;
            v[ci] = true;
            sum += cd;

            for(int[] n : g[ci]){
                pq.add(n);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 0; i<=N; i++)
            g[i] = new ArrayList<>();

        for(int i = 0; i<N; i++){
            int cur = read();
            g[0].add(new int[]{i+1, cur});
        }
        
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                int cur = read();
                if(i == j)
                    continue;
                g[i].add(new int[]{j, cur});
            }
        }
        System.out.print(prim());
    }
}