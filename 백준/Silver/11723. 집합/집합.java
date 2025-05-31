import java.util.*;
import java.io.*;

/*
d, e, h, o,l, m
두번째글자로 판독

20칸짜리 boolean 배열 넣어서 바꾸는식으로 ㄱ

d : 추가 (있으면 추가 x)
e : 제거 (없으면 제거 x)
h : 존재여부 1, 0 출력
o : 있으면 제거, 없으면 추가 (반전)
l : 죄다 true
m : 죄다 false
 */

public class Main {
    private static final String[] keywords = {"add", "remove", "check", "toggle", "all", "empty"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        boolean[] bl = new boolean[20];
        int N = Integer.parseInt(st.nextToken());
        while(N>0){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int num = 0;
            if(!(cmd.equals(keywords[4]) || cmd.equals(keywords[5])))
                num = Integer.parseInt(st.nextToken()) - 1;

            // add
            if(cmd.equals(keywords[0])){
                if(!bl[num])
                    bl[num] = true;
            }
            // remove
            else if (cmd.equals(keywords[1])) {
                if(bl[num])
                    bl[num] = false;
            }
            // check
            else if (cmd.equals(keywords[2])) {
                if(bl[num])
                    sb.append(1);
                else
                    sb.append(0);
                sb.append("\n");
            }
            // toggle
            else if (cmd.equals(keywords[3])) {
                bl[num] = !bl[num];
            }
            // all
            else if (cmd.equals(keywords[4])) {
                Arrays.fill(bl, true);
            }
            // empty
            else if (cmd.equals(keywords[5])) {
                Arrays.fill(bl, false);
            }
            N--;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
