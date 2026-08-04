import java.util.*;
import java.io.*;

/*
prefix, suffix 구해서 곱해주기 O(N)
pairing 경우의 수 구하는 거니까 
왼쪽 조건 맞았을 때 오른쪽 경우의 수 합하기
*/

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        int N = A.length;

        int[] s = new int[N+1];

        for(int i = N-2; i>=0; i--){
            int d = 0;
            if((A[i] == ')' && A[i+1] == ')'))
                d = 1;
            s[i] = s[i+1] + d;
        }

        long ans = 0;
        for(int i = 1; i<N; i++){
            if(A[i] == '(' && A[i-1] =='(')
                ans += s[i];
        }
        System.out.print(ans);
    }
}