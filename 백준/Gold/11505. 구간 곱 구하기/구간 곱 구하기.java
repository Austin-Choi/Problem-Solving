/*
입력배열을 받고
변화 빈번하게 일어나니까 세그먼트 트리임
세그먼트 트리는 완전 이진 트리이며
리프노드에 입력 값들이 들어가는데
이 값들이 전체 트리에 전파되며 최신 계산값으로 값을 재활용하게 해주는
자료구조임

완전 이진이니까 4N으로 크기 잡기

 */

import java.io.*;
import java.util.*;
public class Main {
    static final int MOD = 1_000_000_007;
    static long[] tree;
    static int N, M;

    static void init(long[] arr){
        N = arr.length;
        int size = 1;
        while (size < N)
            size <<= 1;
        tree = new long[size << 1];
        // 루트부터
        build(arr, 1, 0, N-1);
    }

    // node : 현재 트리 인덱스, start-end: 해당 노드 구간
    static void build(long[] arr, int node, int start, int end){
        // 리프노드
        if(start == end){
            tree[node] = arr[start] % MOD;
        }
        else {
            int mid = (start + end) / 2;
            build(arr, node * 2, start, mid);
            build(arr, node * 2 +1, mid+1, end);
            tree[node] = (tree[node*2] * tree[node*2+1]) % MOD;
        }
    }

    // arr[idx]를 value로 바꿧을 때
    static void update(int node, int start, int end, int idx, long val){
        if(start == end){
            tree[node] = val % MOD;
        }
        else{
            int mid = (start + end) / 2;
            if(idx <= mid){
                update(node*2, start, mid, idx, val);
            }
            else{
                update(node*2+1, mid+1, end, idx, val);
            }
            tree[node] = (tree[node*2] * tree[node*2+1]) % MOD;
        }
    }

    // 구간곱 반환 l,r
    static long query(int node, int start, int end, int l, int r){
        // 안겹침 -> 곱 항등원 1 반환
        if(r < start || end < l)
            return 1;
        // 구간 완전히 포함됨
        if(l <= start && end <= r)
            return tree[node];
        int mid = (start + end) / 2;
        long left = query(node*2, start, mid, l, r);
        long right = query(node*2+1, mid+1, end, l, r);
        return (left * right) % MOD;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
            int c = Integer.parseInt(st.nextToken());
            if(type == 1){
                update(1, 0, N-1, b-1,c);
            }
            else{
                long ans = query(1, 0, N-1, b-1,c-1);
                bw.write(String.valueOf(ans));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
