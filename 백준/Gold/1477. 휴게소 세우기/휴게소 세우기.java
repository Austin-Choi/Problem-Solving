import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] pos = new int[N + 2];
        pos[0] = 0;
        pos[N + 1] = L;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) 
            pos[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(pos);

        int lo = 1, hi = L, ans = L;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long need = 0;
            for (int i = 1; i < pos.length; i++) {
                int g = pos[i] - pos[i - 1];
                if (g > mid) {
                    need += (g - 1) / mid;
                    if (need > M) 
                        break;
                }
            }
            if (need <= M) { 
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        System.out.println(ans);
    }
}