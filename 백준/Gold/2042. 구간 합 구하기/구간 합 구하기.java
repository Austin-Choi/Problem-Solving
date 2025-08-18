import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static long[] tree;

    static void init(long[] arr){
        int size = 1;
        while(size < N)
            size <<= 1;
        size <<= 1;
        tree = new long[size];
        build(arr, 1, 0, N-1);
    }

    static void build(long[] arr, int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
        }
        else{
            int mid = (start+end)/2;
            build(arr, node*2, start, mid);
            build(arr, node*2+1, mid+1, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    static void update(int node, int start, int end, int idx, long val){
        if(start == end){
            tree[node] = val;
        }
        else{
            int mid = (start + end) / 2;
            if(idx <= mid){
                update(node*2, start, mid, idx, val);
            }
            else{
                update(node*2+1, mid+1, end, idx, val);
            }
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    static long query(int node, int start, int end, int l, long r){
        if(start > r || end < l)
            return 0;
        if(start >= l && end <= r)
            return tree[node];
        int mid = (start + end) / 2;
        long left = query(node*2, start, mid, l, r);
        long right = query(node*2+1, mid+1, end, l, r);
        return left + right;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M += Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        for(int i = 0; i<N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        init(arr);
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(type == 1){
                update(1, 0, N-1, b-1, c);
            }
            else{
                long ans = query(1, 0, N-1, b-1, c-1);
                bw.write(String.valueOf(ans));
                bw.newLine();
            }
            bw.flush();
        }
    }
}
