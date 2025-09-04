import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static long[] arr;
    static long[] tree;

    static void init(){
        int size = 1;
        while(size < N)
            size <<= 1;
        tree = new long[size << 1];
        build(1, 0, N-1);
    }

    static void build(int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
        }
        else{
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build(node * 2 + 1, mid + 1, end);
            tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        }
    }

    static long query(int node, int start, int end, int l, int r){
        if(r < start || end < l){
            return Long.MAX_VALUE;
        }
        else if(l <= start && end <= r){
            return tree[node];
        }
        else{
            int mid = (start + end) / 2;
            long a = query(node * 2, start, mid, l, r);
            long b = query(node * 2 + 1, mid + 1, end, l, r);
            return Math.min(a,b);
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for(int i = 0; i<N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        init();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            System.out.println(query(1, 0, N-1, l-1, r-1));
        }
    }
}
