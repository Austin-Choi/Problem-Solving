import java.util.*;
import java.io.*;

/*
안겹치는경우 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int[] a = new int[4];
        int[] b = new int[4];
        for(int i = 0; i<4; i++)
            a[i] = read();
        for(int i = 0; i<4; i++)
            b[i] = read();    
        
        // a가 b왼쪽에 있음, a가 b 아래쪽에, a가 b위쪽에, a가 b 오른쪽에
        if(a[2]<b[0] || a[3] < b[1] || a[1] > b[3] || a[0] > b[2])
            System.out.print("nonoverlapping");
        else
            System.out.print("overlapping");
    }
}