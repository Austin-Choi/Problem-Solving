import java.util.*;
import java.io.*;

/*
union할때 cost 적은쪽으로 루트 번호 고르기?
M이 최대 10만인데 union 입력대로 하고 나서 최소 비용으로 연결하려면 정렬이 필요하거나 해야할거같은데
cost가 unique하지 않아서 해쉬맵쓰기도 좀 그렇고 list로 달면 될거같긴한데 그럼 복잡할거같고..

아니면 cost[find(i)]을 set으로 구해서 최소힙에 넣고
최소힙 크기 1개 될때까지 
2개 꺼내서 합치고 다시넣고 반복?
-> XXXXXXXXX
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int[] cost;
    static int[] parent;
    static final int INF = 100000001;

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
        if(cost[pb] < cost[pa]){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        parent = new int[N+1];
        cost = new int[N+1];

        for(int i = 1; i<=N; i++){
            cost[i] = read();
            parent[i] = i;
        }

        while(M-->0){
            union(read(), read());
        }

        // Set 쓰나 visited 쓰나 둘다 어차피 find() N번 호출이라 비슷한듯..
        ArrayList<Integer> s = new ArrayList<>();
        boolean[] v = new boolean[N+1];
        for(int i = 1; i<=N; i++){
            int pi = find(i);
            if(v[pi])
                continue;
            v[pi] = true;
            s.add(pi);
        }

        /*
        10 10 20 30 40
        10+10
        10+20
        10+30
        10+40
        -> sum + (N-2) * min
        제일 작은걸 중앙으로 잡고 연결함
        */
        long sum = 0;
        int min = INF;
        for(int n : s){
            sum += cost[n];
            min = Math.min(min, cost[n]);
        }

        long ans = s.size() == 1 ? 0 : sum + (long)(s.size()-2)*min;
        System.out.print(ans > K ? "NO" : ans);
    }
}