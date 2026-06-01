import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    /*
    a-------b
         c-----d
    a----b  c----d
    */

    public static void main(String[] args) throws IOException{
        int a = read();
        int b = read();
        int c = read();
        int d = read();

        if(a > c){
            int t = a; a = c; c = t;
            t = b; b = d; d = t;
        }

        int tot = Math.max(b,d) - a;
        int overlap = Math.max(c-b, 0);
        System.out.print(tot - overlap);
    }
}