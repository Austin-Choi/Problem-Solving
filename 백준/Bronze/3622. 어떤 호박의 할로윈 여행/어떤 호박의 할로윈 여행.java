/*
각각이 판 안에 들어가야 함
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int A,a,B,b,P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        if(A>P || B>P){
            System.out.print("No");
            return;
        }

        // 딱히 a와 b가 다른 고리지 대소가 정해지지 않음
        if(A+B<=P || A<=b || B <= a){
            System.out.print("Yes");
        }
        else 
            System.out.print("No");
    }
}
