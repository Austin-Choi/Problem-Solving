import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, T;
    static int[] A;
    static int[] copy;

    public static void main(String[] args) throws IOException{
        N = read();
        T = read();

        int size = 3*N;
        A = new int[size];
        copy = new int[size];

        for(int i = 0; i<size; i++)
            A[i] = read();

        for(int i = 0; i<size; i++)
            copy[(i+T)%size] = A[i];

        StringBuilder sb = new StringBuilder();
        for(int a = 0; a<3; a++){
            int limit = (a+1)*N;
            for(int i = a*N; i<limit; i++)
                sb.append(copy[i]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}