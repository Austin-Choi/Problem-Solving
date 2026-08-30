import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read()};
        }
        Arrays.sort(A, (a,b)->{
            return a[1] - b[1];
        });

        int e = A[0][1];
        int cnt = 1;
        for(int i = 1; i<N; i++){
            if(A[i][0] > e){
                e = A[i][1];
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}