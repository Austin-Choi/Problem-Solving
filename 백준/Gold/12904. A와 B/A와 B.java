/*
1) 뒤가 A이면 뒤의 A 제거
2) 뒤가 B이면 뒤의 B 제거하고 뒤집기

연산에 의해 항상 짧아지므로
길이가 s=t 가 되면
equals 해보기

StringBuilder 쓰기
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder t = new StringBuilder(T);
        while(t.toString().length() >= S.length()){
            if(t.toString().length() == S.length()){
                if(t.toString().equals(S))
                    bw.write("1");
                else
                    bw.write("0");

            }

            if(t.toString().charAt(t.length()-1) == 'B') {
                t.deleteCharAt(t.length()-1);
                t.reverse();
            }
            else
                t.deleteCharAt(t.length()-1);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}