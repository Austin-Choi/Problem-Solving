import java.util.*;
import java.io.*;

/*
수열 A에서 M짜리 구간 탐색하면서 빈도체크?
*/

public class Main {
    static int N,M;
    static int[] A,B;

    static boolean isSame(int[] a, int[] b){
        for(int i = 0; i<a.length; i++){
            if(a[i] != b[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(M>N){
            System.out.print(0);
            return;
        }

        A = new int[N];
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] countA = new int[101];
        int[] countB = new int[101];
        for(int i = 0; i<M; i++){
            B[i] = Integer.parseInt(st.nextToken());
            countA[A[i]]++;
            countB[B[i]]++;
        }

        int ans = 0;
        //초기 윈도 검사
        if(isSame(countA, countB))
            ans++;

        for(int i = M; i<N; i++){
            countA[A[i-M]]--;
            countA[A[i]]++;
            if(isSame(countA, countB))
                ans++;
        }

        System.out.print(ans);
    }
}