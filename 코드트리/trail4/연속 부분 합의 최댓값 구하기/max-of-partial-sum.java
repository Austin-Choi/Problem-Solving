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
        int cur = -1001;
        int ans = -1001;
        for(int i = 0; i<N; i++){
            int x = read();
            cur = Math.max(x, cur + x);
            ans = Math.max(ans, cur);
        }
        System.out.print(ans);
    }
}