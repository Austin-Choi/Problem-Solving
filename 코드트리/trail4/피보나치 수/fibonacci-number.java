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
        long[] p = new long[N];
        p[0] = 1;
        if(N > 1)
            p[1] = 1;
        for(int i = 2; i<N; i++){
            p[i] = p[i-2] + p[i-1];
        }
        System.out.print(p[N-1]);
    }
}