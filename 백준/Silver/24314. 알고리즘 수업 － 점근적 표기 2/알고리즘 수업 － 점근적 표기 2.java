import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a1 = Long.parseLong(st.nextToken());
        long a0 = Long.parseLong(st.nextToken());
        long c = Long.parseLong(br.readLine());
        long n0 = Long.parseLong(br.readLine());

        int ans = (a1 >= c && a1 * n0 + a0 >= c * n0) ? 1 : 0;
        System.out.print(ans);
    }
}
