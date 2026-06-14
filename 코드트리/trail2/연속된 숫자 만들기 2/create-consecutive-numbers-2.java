import java.util.*;
import java.io.*;

/*
479
*/
public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int[] A = new int[3];
        for(int i= 0; i<3; i++){
            A[i] = read();
        }
        Arrays.sort(A);
        if(A[0]+1 == A[1] && A[1]+1 == A[2])
            System.out.print(0);
        else if(A[0]+2 == A[1] || A[1] + 2 == A[2])
            System.out.print(1);
        else
            System.out.print(2);
    }
}