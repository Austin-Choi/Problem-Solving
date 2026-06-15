import java.util.*;
import java.io.*;

/*
M개 간선 정보대로 union 연산 수행
배열 cnt[N+1]에 i=[1,N] 순회하면서 cnt[find(i)]++

2-3 4-3
2-4

1 2 3 4 5
  2 2 2

음 근데 find(a) find(b)로 항상 루트노드를 갖게 탐지하나..
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,A,B,K;
    static int[] parent;
    static int[] size;
    static int[] cnt;

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
        if(size[pb] < size[pa]){
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
        parent = new int[N+1];
        size = new int[N+1];
        cnt = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(M-->0){
            union(read(), read());
        }
        A = find(read());
        B = find(read());
        K = read();

        // 루트노드마다 얼마나 연결되어있는지 갯수셈
        for(int i = 1; i<=N; i++){
            int cur = find(i);
            if(cur == B)
                continue;
            cnt[cur]++;
        }

        // 최대 힙을 쓰게되면 N log N
        // 최소 힙을 쓰게되면 N log K
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i= 1; i<=N; i++){
            // A가 루트노드인건 이미 연결되어있으므로 ans 초기값으로 설정
            // B가 루트노드인건 넣으면 안됨
            if(i == A || i == B || cnt[i] == 0)
                continue;
            if(pq.size() < K)
                pq.add(cnt[i]);
            else if(K > 0 && cnt[i] > pq.peek()){
                pq.poll();
                pq.add(cnt[i]);
            }
        }

        int ans = cnt[A];
        // K번 연결하랬으니 제일 많이 연결된 노드 순서로 K번 연결 시도
        // -> pq 크기 k로 유지하기
        while(!pq.isEmpty()){
            ans += pq.poll();
        }
        System.out.print(ans);
    }
}