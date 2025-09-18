/*
5
2 0 3 1 4
0 1 2 3 4

B열 해싱해서 A열을 B형 인덱스로 나타내고
역순쌍을 세야함
겹치는것을 세야 하므로 전깃줄 문제와는 반대로

뒤에서부터 쭉 훑는데 0 ~ 현재 원소값 -1 까지 해서
현재 원소보다 작은게 얼마나 등장했는지 query+query 재귀하는 형태로 구간합을 구하고
ans에 해당 구간합을 
update로 현재 원소가 등장했음을 기록하고 반복

2 0 3 1 4면
4 넣을때 ans += 0
1 넣을때 ans += 0
3 넣을때 ans += 1
0 넣을때 ans += 0
2 넣을때 ans += 2
답 : 3
 */
import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[] A,B,idx;
    static int[] tree;

    static void init(){
        int size = 1;
        while(size<N)
            size <<= 1;
        tree = new int[size<<1];
    }
    // 구간합
    // 지금까지 등장한 값 중 자기보다 작은 것 갯수 확인
    static int query(int node, int start, int end, int l, int r){
        if(r < start || l > end)
            return 0;
        if(l <= start && end <= r)
            return tree[node];
        int mid = (start+end)/2;
        return query(node*2 , start, mid, l, r) + query(node*2+1, mid+1, end, l, r);
    }

    // 오른쪽 -> 왼쪽으로 스캔하면서
    // idx 위치에 값의 등장 횟수 더하기 (역순쌍이니까 1)
    // -> 이 값 등장을 기록함
    static void update(int node, int start, int end, int idx, int val){
        if(idx<start || idx > end)
            return;
        tree[node] += val;
        if(start != end){
            int mid = (start + end)/2;
            update(node*2, start, mid, idx, val);
            update(node*2+1, mid+1, end, idx, val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        idx = new int[1_000_001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            B[i] = Integer.parseInt(st.nextToken());
            idx[B[i]] = i;
        }

        // A를 B인덱스 순열로 변환
        for(int i = 0; i<N; i++){
            A[i] = idx[A[i]];
        }

        init();
        long ans = 0;

        for(int i = N-1; i>=0; i--){
            int cur = A[i];
            if(cur > 0)
                // 자기보다 작은 원소 갯수 더하기
                ans += query(1,0,N-1, 0, cur-1);
            update(1,0,N-1, cur, 1);
        }

        System.out.println(ans);
    }
}
