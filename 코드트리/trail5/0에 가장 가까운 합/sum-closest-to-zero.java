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
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }
        Arrays.sort(A);

        int l = 0;
        int r = N-1;
        int ans = Integer.MAX_VALUE; 
        int sum = 0;
        while(l<r){
            sum = A[l] + A[r];
            ans = Math.min(ans, Math.abs(A[l] + A[r]));

            if(sum > 0)
                r--;
            else if(sum < 0)
                l++;
            else
                break;
        }
        System.out.print(ans);
    }
}