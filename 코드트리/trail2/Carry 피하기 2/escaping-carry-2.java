import java.util.*;
import java.io.*;

/*
1) 3개의 수가 carry 발생시키는 여부 구하는 함수 
-> base 1잡아놓고 
-> 수의 범위가 10^4
base 10 곱해가면서 base로 각 숫자 나머지연산하고 그 결과 더했을때 10 안넘는지 확인

2) 서로다른 3개 뽑아서 1번 만족할때 합 구해서 최댓값으로 갱신
*/

public class Main {
    static int N;
    static int[] A;

    static boolean hasCarry(int a, int b, int c){
        int base = 1;
        while(base <= 10_000){
            int aa = (a/base)%10;
            int bb = (b/base)%10;
            int cc = (c/base)%10;
            if(aa+bb+cc>9)
                return true;
            base *= 10;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        int ans = -1;

        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i<N-2; i++){
            for(int j = i+1; j<N-1; j++){
                for(int k = j+1; k<N; k++){
                    if(i != j && j != k && i != k){
                        if(!hasCarry(A[i], A[j], A[k]))
                            ans = Math.max(ans, A[i]+A[j]+A[k]);
                    }
                }
            }
        }
        System.out.print(ans);
    }
}