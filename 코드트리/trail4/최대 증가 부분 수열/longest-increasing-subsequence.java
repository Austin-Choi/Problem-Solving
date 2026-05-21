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
        int[] a = new int[N];
        for(int i= 0; i<N; i++){
            a[i] = read();
        }   

        List<Integer> li = new ArrayList<>();
        for(int n : a){
            if(li.isEmpty() || li.get(li.size()-1) < n){
                li.add(n);
            }
            else{
                int idx = Collections.binarySearch(li, n);
                if(idx < 0)
                    idx = -(idx+1);
                li.set(idx, n);
            }
        }
        System.out.print(li.size());
    }
}