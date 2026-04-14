import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            int B = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if (B == -1 && E == -1)
                break;
            String s = line.substring(
                    line.indexOf(' ', line.indexOf(' ')+1)+1);

            // B 이전 현재 열린 태그 저장
            Stack<String> st1 = new Stack<>();
            // B~E 잘린 구간까지 포함
            Stack<String> st2;
            for (int i = 0; i < B; ) {
                if (s.charAt(i) == '<') {
                    int j = s.indexOf('>', i);
                    String t = s.substring(i, j + 1);
                    if (t.charAt(1) == '/')
                        st1.pop();
                    else
                        st1.push(t);
                    i = j + 1;
                }
                else
                    i++;
            }

            st2 = (Stack<String>) st1.clone();
            for (int i = B; i < E; ) {
                if (s.charAt(i) == '<') {
                    int j = s.indexOf('>', i);
                    String t = s.substring(i, j + 1);
                    if (t.charAt(1) == '/')
                        st2.pop();
                    else
                        st2.push(t);
                    i = j + 1;
                }
                else
                    i++;
            }

            // st1 + s[B:E] + st2 남은거
            StringBuilder sb = new StringBuilder();
            for (String t : st1)
                sb.append(t);
            sb.append(s, B, E);
            while (!st2.isEmpty()) {
                String t = st2.pop();
                sb.append("</").append(t.substring(1));
            }
            System.out.println(sb);
        }
    }
}
