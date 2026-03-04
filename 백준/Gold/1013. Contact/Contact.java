/*
(100+1+ | 01)+
regex문제
 */
import java.util.regex.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Pattern p = Pattern.compile("^(100+1+|01)+$");
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            String s = br.readLine();
            Matcher m = p.matcher(s);
            if(m.matches())
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }
        System.out.print(sb);
    }
}