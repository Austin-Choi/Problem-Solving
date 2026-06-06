import java.util.*;
import java.io.*;

/*
실제로 옮기지 말고 T = offset 만 저장해서 

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,T;
    static int[] A;
    static int[] copy;


    public static void main(String[] args) throws IOException{
        N = read();
        T = read();

        A = new int[2*N];
        copy = new int[2*N];
        for(int i = 0; i<2*N; i++){
            A[i] = read();
        }

        for(int i = 0; i<2*N; i++){
            copy[(i+T)%(2*N)] = A[i]; 
        }

        StringBuilder sb = new StringBuilder();
        for(int  i = 0; i<copy.length; i++){
            sb.append(copy[i]);
            if(i == copy.length/2-1)
                sb.append("\n");
            else
                sb.append(" ");
        }
        System.out.print(sb);
    }
}