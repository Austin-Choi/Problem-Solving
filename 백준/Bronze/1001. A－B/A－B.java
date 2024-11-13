import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int add(int a, int b){
        while(b != 0){
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
    public static int subtract(int a, int b){
        return add(a, add(~b, 1));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int answer = a-b;
        //int answer2 = subtract(a,b);
        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
