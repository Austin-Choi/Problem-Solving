import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long w = Long.parseLong(st.nextToken());
        long s = Long.parseLong(st.nextToken());

        long cost1 = (x+y)*w;
        long cost2 = Math.min(x,y) * s + Math.abs(x-y) * w;
        long cost3;
        if((x+y)%2 == 0)
            cost3 = Math.max(x,y) * s;
        else
            cost3 = (Math.max(x,y) - 1)* s + w;

        bw.write(String.valueOf(Math.min(Math.min(cost1, cost2), cost3)));
        bw.flush();
        bw.close();
        br.close();
    }
}
