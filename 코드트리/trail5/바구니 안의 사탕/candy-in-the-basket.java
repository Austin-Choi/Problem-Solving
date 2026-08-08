import java.util.*;
import java.io.*;

/*
r = l+2*k
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        TreeMap<Integer, Long> m = new TreeMap<>();
        int K = read();
        for(int i = 0; i<N; i++){
            int amt = read();
            int pos = read();
            m.put(pos, m.getOrDefault(pos, 0L)+ amt);
        }

        int size = m.keySet().size();
        int[] pos = new int[size];
        long[] candy = new long[size];
        int idx = 0;
        for(Map.Entry<Integer, Long> e : m.entrySet()){
            pos[idx] = e.getKey();
            candy[idx] = e.getValue();
            idx++;
        }

        int l = 0;
        int r = 0;
        long sum = 0;
        long ans = 0;
        while(r < size){
            if(pos[r] - pos[l] <= 2*K){
                sum += candy[r];
                ans = Math.max(ans, sum);
                r++;
            }
            else{
                sum -= candy[l];
                l++;
            }
        }
        System.out.print(ans);
    }
}