import java.util.*;
import java.io.*;

/*
사탕갯수, 1차원상 좌표
[]는 c-K <= X <= c+K

일단 A를 A[][0] 오름차순으로 정렬해서
c의 범위는 A[0][0] ~ A[N-1][0]이 되도록 함
-> 정렬하면 n log n이니까 그냥 입력받을때 최대 최소값 갱신하기
*/

public class Main {
    static int N, K;
    static int[] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[101];
        int[] ps= new int[102];

        // 유효한 idx가 중앙값으로 잡혀야될듯
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int candy = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            // 같은 위치에 여러 바구니 가능!!!!
            A[idx] += candy;
        }

        for(int i = 0; i<101; i++){
            ps[i+1] = A[i] + ps[i];
        }
        
        int ans = 0;
        for(int s = 0; s<=100; s++){
            // 이상 이하라서 끝점에 +1
            int curSum = ps[Math.min(100, s+K)+1] - ps[Math.max(0, s-K)];
            ans = Math.max(ans, curSum);
        }    
        System.out.print(ans);
    }
}