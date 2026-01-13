/*
n번째 666이 들어간 수
-> 문자열로 처리
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int a = 0;
        int cnt = 0;
        while(cnt < n){
            a++;
            if(String.valueOf(a).contains("666"))
                cnt++;
        }
        System.out.print(a);
    }
}
