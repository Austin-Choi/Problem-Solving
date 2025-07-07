import java.io.*;
import java.util.*;

class Info implements Comparable<Info>{
    int size;
    int candy;

    public Info(int size, int candy){
        this.candy = candy;
        this.size = size;
    }

    @Override
    public int compareTo(Info o){
        return o.candy - this.candy;
    }
}

public class Main {
    private static int N,M,K;
    // i로 초기화
    private static int[] parent;
    // 아이들 한명씩은 있으니 1로 초기화
    private static int[] size;
    // 각 정점별 사탕 수
    private static int[] candy;
    private static int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    private static void union(int x, int y){
        if(y >= parent.length)
            return;
        x = find(x);
        y = find(y);
        if(x == y)
            return;
        // 큰걸 작은쪽에 달아주기
        if(size[x] < size[y]){
            parent[x] = y;
            size[y] += size[x];
            candy[y] += candy[x];
        }
        else{
            parent[y] = x;
            size[x] += size[y];
            candy[x] += candy[y];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }

        size = new int[N+1];
        Arrays.fill(size, 1);

        candy = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            candy[i] = Integer.parseInt(st.nextToken());
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s,e);
        }

        Set<Integer> s = new HashSet<>();
        List<Info> items = new ArrayList<>();
        for(int i = 1; i<=N; i++){
            int root = find(i);
            if(!s.contains(root)) {
                s.add(root);
                items.add(new Info(size[root], candy[root]));
            }
        }

        // dp[i] = 무게가 i일때 최대 가치
        int[] dp = new int[K+1];
        for(Info i : items){
            int w = i.size;
            int v = i.candy;

            // K가 공명하는 최소 수랬으니
            // K-1에서부터 돌려야 1개씩만 뽑는 0-1배낭됨
            for(int n = K-1; n - w >= 0; n--){
                dp[n] = Math.max(dp[n], dp[n-w] + v);
            }
        }

        int ans = 0;
        for(int i = 0; i<=K; i++){
            ans = Math.max(ans, dp[i]);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
