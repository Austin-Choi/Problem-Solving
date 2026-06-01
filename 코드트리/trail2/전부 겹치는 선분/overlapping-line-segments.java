import java.util.*;
import java.io.*;

/*
모든 선분에 대해 교집합을 업데이트하면서 
마지막에 교집합의 l, r값이 valid한지
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] A;

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read()};
        } 
        int l = -1;
        int r = 101;
        for(int[] aa : A){
            l = Math.max(l, aa[0]);
            r = Math.min(r, aa[1]);
        }

        if(l <= r)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}