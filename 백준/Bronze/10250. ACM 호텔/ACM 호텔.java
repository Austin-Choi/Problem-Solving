/*
N번째 방 번호 출력하기
/H+1 결과 맨 오른쪽, 한자리수면 앞에 0 붙이기
%H+1 결과 맨 왼쪽, 한자리수면 앞에 0 붙이기
 */
import java.util.*;
import java.io.*;
public class Main {
    static String format(int i, int j){
        return String.format("%d%02d", i, j);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            // 12, 602
            if(n%h == 0)
                sb.append(format(h,n/h)).append("\n");
            else
                sb.append(format(n%h, n/h+1)).append("\n");
        }
        System.out.print(sb);
    }
}
