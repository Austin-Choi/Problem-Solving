import java.util.*;
import java.io.*;

/*
N이 최대 100개라 비트마스크 안될거같고
1-2-3-4-...-N-1-N 순서가 정해져있음
1,N은 건너뛰지 않음.

음.. 차라리 그래프로 해서 다음거랑 그 다음거 잇는 그래프로 만들고
지금 해당 노드가 활성화됬는지 여부로 건너뛰기

근데 다음 상태가 건너뛰거나 안 건너뛰거나 경우라서 
o(1)도 가능할듯
*/

public class Main {
    static int N;
    static int[][] A;
    // A의 인덱스 번호
    static ArrayList<Integer>[] g;
    static int getDist(int[] a, int[] b){
        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
    }

    // 0번에서 시작
    static long getTot(int invalid){
        // 시작 번호, 거리
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(ci == N-1)
                return cd;

            if(ci == N-2)
                q.add(new int[]{ci+1, cd + getDist(A[ci], A[ci+1])});
            else{
                int ni, nd;
                if(ci + 1 == invalid)
                    ni = ci + 2;
                else
                    ni = ci + 1;
                nd = cd + getDist(A[ci], A[ni]);
                q.add(new int[]{ni,nd});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][2];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        // 매번 전체 연산하는 대신 선택하거나 말거나니까 누적합 해보는건 어떨지
        long ans = 100 * 1_000_000 + 1;
        // for(int i = 1; i<N-1; i++){
        //     ans = Math.min(ans, getTot(i));
        // }
        long total = 0;
        for(int i = 0; i<N-1; i++){
            total += getDist(A[i], A[i+1]);
        }

        for(int k = 1; k<N-1; k++){
            long cur = total - getDist(A[k-1], A[k]) - getDist(A[k],A[k+1]) + getDist(A[k-1],A[k+1]);
            ans = Math.min(ans, cur);
        }

        System.out.print(ans);
    }
}
