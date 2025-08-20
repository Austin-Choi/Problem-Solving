import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] tree;
    static void init(int[] arr){
        int size = 1;
        while(size < N)
            size <<= 1;
        tree = new int[size << 1];
        build(arr, 1, 0, N-1);
    }

    static void build(int[] arr, int node, int start, int end){
        if(start == end){
            tree[node] = start;
        }
        else{
            int mid = (start + end) / 2;
            build(arr, node * 2, start, mid);
            build(arr, node *2 + 1, mid+1, end);
            if(arr[tree[node * 2]] == arr[tree[node * 2 + 1]]){
                tree[node] = Math.min(tree[node*2] , tree[node*2+1]);
            }
            else{
                if(arr[tree[node * 2]] < arr[tree[node * 2 + 1]]){
                    tree[node] = tree[node * 2];
                }
                else
                    tree[node] = tree[node * 2 + 1];
            }
        }
    }

    static void update(int[] arr, int node, int start, int end, int idx, int val){
        if(start == end){
            tree[node] = start;
            arr[idx] = val;
        }
        else{
            int mid = (start + end) / 2;
            if(idx <= mid){
                update(arr, node * 2, start, mid, idx, val);
            }
            else{
                update(arr,node * 2 + 1, mid+1, end, idx, val);
            }
            if(arr[tree[node * 2]] == arr[tree[node * 2 + 1]]){
                tree[node] = Math.min(tree[node*2] , tree[node*2+1]);
            }
            else{
                if(arr[tree[node * 2]] < arr[tree[node * 2 + 1]]){
                    tree[node] = tree[node * 2];
                }
                else
                    tree[node] = tree[node * 2 + 1];
            }
        }
    }

    static int query(int[] arr, int node, int start, int end, int l, int r){
        if(l > end || r < start)
            return -1;
        if(l <= start && end <= r)
            return tree[node];
        int mid = (start + end) / 2;
        int left = query(arr, node * 2, start, mid, l, r);
        int right = query(arr, node * 2+1, mid+1, end, l, r);
        if(left == -1)
            return right;
        if(right == -1)
            return left;
        if(arr[left] == arr[right])
            return Math.min(left, right);
        if(arr[left] < arr[right])
            return left;
        else
            return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(arr);
        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(type == 1){
                update(arr, 1, 0, N-1, b-1, c);
            }
            else{
                int ans = query(arr, 1, 0, N-1, b-1, c-1);
                System.out.println(ans+1);
            }
        }
    }
}
