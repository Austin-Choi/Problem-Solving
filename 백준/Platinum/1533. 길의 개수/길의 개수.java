/*
행렬의 제곱 -> i->j 경로의 길이가 n인 경로의 갯수니까
문제에서 가중치가 5이하라서
N*5 * N*5로 확장시키고
A행렬 ^ T 에서 A[s][e] 확인하기

u->v (w<=5)
u->v,1->v,2-> ... -> v,w

(v,0) : 이동완료상태
(v,k>0) : 이동 중, k초 남음
시간 1초 : 행렬 제곱한번 한거
 */
import java.io.*;
import java.util.*;
public class Main {
    static int N,S,E;
    static long T;
    static final long MOD = 1_000_003L;

    static long[][] multiply(long[][] A, long[][] B){
        int n = A.length;
        long[][] C = new long[n][n];
        for(int i = 0; i<n; i++){
            for(int k = 0; k<n; k++){
                if(A[i][k] == 0)
                    continue;
                long aik = A[i][k];
                for(int j = 0; j<n; j++){
                    C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    static long[][] base(int n){
        long[][] b = new long[n][n];
        for(int i = 0; i<n; i++)
            b[i][i] = 1;
        return b;
    }

    static long[][] power(long[][] A, long exp){
        int n = A.length;

        if(exp == 0)
            return base(n);
        if(exp == 1)
            return A;

        long[][] half = power(A, exp/2);
        long[][] rst = multiply(half, half);

        if(exp % 2 == 1)
            rst = multiply(rst, A);

        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken())-1;
        E = Integer.parseInt(st.nextToken())-1;
        T = Long.parseLong(st.nextToken());

        long[][] A = new long[N*5][N*5];

        for(int v = 0; v<N; v++){
            for(int t = 1; t<5; t++){
                // 시간 상태를 감소로
                // (v, 0)을 이동완료 상태로 고정시킴
                A[v*5+t][v*5 + (t-1)] = 1;
            }
        }

        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                int w = temp[j] - '0';
                if(w > 0)
                    // i->j일때 가중치만큼 1짜리 간선으로 잘라서 늘림
                    // (v,k)에서 k-> 0~4
                    A[i*5][j*5 + (w-1)] = 1;
            }
        }

        long[][] rst = power(A, T);
        System.out.println(rst[S*5][E*5] % MOD);
    }
}
