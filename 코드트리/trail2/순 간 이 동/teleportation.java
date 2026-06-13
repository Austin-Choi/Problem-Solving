import java.util.*;
import java.io.*;

/*
A B x y
-> A-B

A x B y
-> x-A + y-B

A x y B
-> x-A + B-y

x A y B
-> A-x + B-y

x y A B
-> B-A
------------
조건 나눠서 생각하는게 아니라 dp처럼 하는거같음
1. 텔포 안씀
2. 텔포 씀 X->Y
3. 텔포 씀 Y->X
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int A = read();
        int B = read();
        int x = read();
        int y = read();

        int ans = Math.min(Math.abs(B-A), Math.min(Math.abs(x-A)+Math.abs(y-B), Math.abs(y-A)+Math.abs(x-B)));
        System.out.print(ans);
    }
}