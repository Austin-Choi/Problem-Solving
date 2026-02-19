/*
트라이를 공유 안하면 N*2K
트라이에서 공통 prefix는 정렬하면 인접한 문자열끼리 존재 -> LCP
N*2K - (prefix LCP 길이 + suffix LCP 길이)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int n, k;
    // K길이의 suffix, prefix 모아놓는 리스트
    static List<String> s = new ArrayList<>();
    static List<String> e = new ArrayList<>();

    static int cmp(String a, String b, String c, String d) {
        int i = 0, j = 0;

        if (a.equals(b)) i = k;
        else
            while (i < k && a.charAt(i) == b.charAt(i))
                i++;

        if (c.equals(d)) j = k;
        else
            while (j < k && c.charAt(j) == d.charAt(j))
                j++;

        return i + j;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            // prefix
            s.add(str.substring(0, k));
            // suffix 뒤집기
            String t = str.substring(k, 2 * k);

            StringBuilder sb = new StringBuilder(t);
            e.add(sb.reverse().toString());
        }

        int ans = n * k * 2;

        Collections.sort(s);
        Collections.sort(e);

        for (int i = 1; i < n; i++) {
            ans -= cmp(s.get(i - 1), s.get(i), e.get(i - 1), e.get(i));
        }

        System.out.println(ans);
    }
}
