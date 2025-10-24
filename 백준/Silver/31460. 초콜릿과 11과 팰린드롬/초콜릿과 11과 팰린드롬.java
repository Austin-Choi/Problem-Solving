/*
13579 를 양쪽으로 채우고 중간 전부 9로 채우면 짝수 자리 팰린드롬이고
짝수 : 음..
12221 ㄱㄱ
*/
import java.io.*;
public class Main {
    static int T,N;
    static String build(int L) {
        if (L == 1) return "0";
        StringBuilder sb = new StringBuilder(L);
        sb.append('1');
        for (int i = 0; i < L - 2; i++) sb.append('2');
        sb.append('1');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            sb.append(build(N)).append("\n");
        }
        System.out.println(sb);
    }
}