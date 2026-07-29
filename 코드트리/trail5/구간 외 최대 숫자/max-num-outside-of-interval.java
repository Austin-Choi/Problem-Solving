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
        int Q = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int[] p = new int[N+1];
        int[] s = new int[N+1];
        for(int i = 1; i<=N; i++){
            p[i] = Math.max(p[i-1], A[i-1]);
        }
        for(int i = N-1; i>=0; i--){
            s[i] = Math.max(s[i+1], A[i]);
        }

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();
            sb.append(Math.max(p[a-1], s[b]));
            sb.append("\n");
        }
        System.out.print(sb);
    }
}