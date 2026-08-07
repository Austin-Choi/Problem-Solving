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
        int K = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }
        
        int l = 0;
        int r = 0;
        int ans = 0;
        Map<Integer, Integer> m = new HashMap<>();
        while(l <= r && r < N){
            m.put(A[r], m.getOrDefault(A[r], 0)+1);

            while(m.get(A[r]) > K){
                m.put(A[l], m.getOrDefault(A[l], 0)-1);
                l++;
            }

            ans = Math.max(ans, r - l +1);
            r++;
        }
        System.out.print(ans);
    }
}