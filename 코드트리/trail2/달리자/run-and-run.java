import java.util.*;
import java.io.*;

/*
진행 방향이 왼쪽에서 오른쪽이니까 
pA[i] = 5 9 12 13
pB[i] = 3 5 8 13




5 4 3 1
5 4 0 4 -> 3
5 3 0 5 -> 2
5 0 3 5 -> 3
3 2 3 5 -> 2

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }
        int[] B = new int[N];
        for(int i=0; i<N; i++){
            B[i] = read();
        }
        int[] pA = new int[N];
        pA[0] = A[0];
        for(int i = 1; i<N; i++){
            pA[i] = pA[i-1]+A[i];
        }
        int[] pB = new int[N];
        pB[0] = B[0];
        for(int i = 1; i<N; i++){
            pB[i] = pB[i-1]+B[i];
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            ans += Math.abs(pA[i] - pB[i]);
        }
        System.out.print(ans);
    }
}