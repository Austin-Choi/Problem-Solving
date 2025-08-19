/*
세그트리에는 해당 구간에서의 최소 높이를 가지는 인덱스 저장
가장큰직사각형은 재귀로
최소높이 가진 막대 인덱스 m query로 찾음

재귀 파트
최소 높이 전체 구간 -> arr[m] * (r-l+1)
-> 중앙 기준으로 폭을 계산함
중앙을 제외한
왼쪽 구간 = getRect(l, mid-1)
오른쪽 구간 = getRect(mid+1, r)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] tree;

    static void init(long[] arr){
        int size = 1;
        while(size<N)
            size <<= 1;
        tree = new int[size << 1];
        build(arr, 1, 0, N-1);
    }

    static void build(long[] arr, int node, int start, int end){
        if(start == end){
            tree[node] = start;
        }
        else{
            int mid = (start + end) / 2;
            build(arr, node * 2, start, mid);
            build(arr, node * 2 + 1, mid+1, end);
            tree[node] = (arr[tree[node*2]] <= arr[tree[node*2+1]] ? tree[node * 2] : tree[node * 2 + 1]);
        }
    }

    static int query(long[] arr, int node, int start, int end, int l, int r){
        if(l > end || r < start)
            return -1;
        if(l <= start && end <= r)
            return tree[node];
        int mid = (start + end)/2;
        int left = query(arr, node *2, start, mid, l, r);
        int right = query(arr, node*2+1, mid+1, end, l, r);
        if(left == -1)
            return right;
        if(right == -1)
            return left;
        if(arr[left] <= arr[right])
            return left;
        else
            return right;
    }

    static long getRect(long[] arr, int l, int r){
        if(l > r)
            return 0;
        if(l == r)
            return arr[l];

        int m = query(arr, 1, 0, N-1, l, r);
        long a = arr[m] * (r-l+1);
        long b = getRect(arr, l, m-1);
        long c = getRect(arr, m+1, r);

        return Math.max(a,Math.max(b,c));
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0)
                break;
            N = num;
            long[] arr = new long[N];
            for(int i =0 ; i<N; i++){
                arr[i] = Long.parseLong(st.nextToken());
            }
            init(arr);
            long ans = getRect(arr, 0, N-1);
            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
