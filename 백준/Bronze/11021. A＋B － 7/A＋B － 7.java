import java.util.*;
import java.io.*;
public class Main {
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static void print(int t, int a){
        sb.append("Case #").append(t).append(": ").append(a).append("\n");
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            print(t, x+y);
        }
        System.out.println(sb);
    }
}
