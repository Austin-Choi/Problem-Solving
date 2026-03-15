/*
n = n-1 + n-2
n+1 = n n-1
1 1
1 0
ad - bc = 0-1 = 1/-1
d -b
-c a
0 1
1 -1
-> 역원에서 MOD가 1e9라서 MOD 계산할때 행렬 변조됨
-> 역행렬로 직접 음수방향 연산 불가능
-> 상태 벡터 잡을때 가장 멀리 떨어진 항의 max 값으로 잡으면 됨

an = n+1 n
     n  n-1
F-n = (-1)^n+1 * Fn
 */
import java.util.*;
import java.io.*;
public class Main {
    static long[][] basic = {{1,1},{1,0}};
    //static long[][] inv = {{0,1},{1,-1}};
    static long[][] t = {{1,0},{0,1}};
    static final long MOD = 1_000_000_000;
    static long[][] multiply(long[][] a, long[][] b){
        long[][] c = new long[2][2];
        for(int i =0; i<2; i++){
            for(int j = 0; j<2; j++){
                for(int k = 0; k<2; k++){
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
    static long[][] exp(int e){
        long[][] base = basic;
        long[][] rst = t;
        while(e > 0){
            if((e&1) == 1)
                rst = multiply(rst, base);
            base = multiply(base, base);
            e >>= 1;
        }
        return rst;
    }

    static int oo(int e){
        if(e > 0)
            return 1;
        else if(e == 0)
            return 0;
        else{
            if((e&1) == 1)
                return 1;
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int e = Integer.parseInt(br.readLine());
        long[][] rst = exp(Math.abs(e));
        // n-1 n-2
        // n n-1 이므로
        System.out.println(oo(e));
        System.out.println(rst[1][0]);
    }
}
