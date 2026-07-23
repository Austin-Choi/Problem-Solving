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
        int[][] work = new int[N][2];
        for(int i = 0; i<N; i++){
            work[i] = new int[]{read(), read()};
        }
        Arrays.sort(work, (a,b)->{
            if(a[1] != b[1])
                return a[1]-b[1];
            return a[0] - b[0];
        });

        int ce = 0;
        int ans = 0;
        for(int i = 0; i<N; i++){
            if(ce <= work[i][0]){
                ce = work[i][1];
                ans++;
            }
        }
        System.out.print(N-ans);
    }
}