import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static long[][] tree;

    static void init(long[] arr){
        int size = 1;
        while(size < N)
            size <<= 1;
        tree = new long[size << 1][2];
        build(arr, 1, 0, N-1);
    }

    static void build(long[] arr, int node, int start, int end){
        if(start == end) {
            tree[node][0] = arr[start];
            tree[node][1] = arr[start];
        }
        else{
            int mid = (start + end) / 2;
            build(arr, node*2, start, mid);
            build(arr, node*2+1, mid+1, end);
            tree[node][0] = Math.min(tree[node*2][0], tree[node*2+1][0]);
            tree[node][1] = Math.max(tree[node*2][1], tree[node*2+1][1]);
        }
    }

    static long[] query(int node, int start, int end, int l, int r){
        if(r < start || l > end)
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        if(l <= start && r >= end)
            return tree[node];
        int mid = (start + end) / 2;
        long[] left = query(node*2, start, mid, l, r);
        long[] right = query(node*2+1, mid+1, end, l, r);
        return new long[]{Math.min(left[0], right[0]), Math.max(left[1], right[1])};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        for(int i = 0; i<N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        init(arr);
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long[] ans = query(1, 0, N-1, a-1,b-1);
            sb.append(ans[0]).append(" ").append(ans[1]).append(" ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
