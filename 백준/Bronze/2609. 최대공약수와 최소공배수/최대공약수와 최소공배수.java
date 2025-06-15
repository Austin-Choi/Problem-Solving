
import java.io.*;
import java.util.*;

public class Main {
    private static int gcd(int a, int b){
        if(a%b == 0)
            return b;
        return gcd(b, a%b);
    }
    private static int lcm(int a, int b, int gcd){
        return gcd * a/gcd * b/gcd;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        sb.append(gcd(a,b)).append("\n").append(lcm(a,b,gcd(a,b)));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}