import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    long i;
    long j;

    public Point(long i, long j) {
        this.i = i;
        this.j = j;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> lp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long tempI = Long.parseLong(st.nextToken());
            long tempJ = Long.parseLong(st.nextToken());
            lp.add(new Point(tempI, tempJ));
        }

        lp.add(lp.get(0));  // 마지막에 첫 번째 좌표를 추가

        // Shoelace 알고리즘 적용
        long total = 0;
        for (int i = 0; i < N; i++) {
            long x1 = lp.get(i).i;
            long x2 = lp.get(i + 1).i;
            long y1 = lp.get(i).j;
            long y2 = lp.get(i + 1).j;
            total += (x1 * y2) - (x2 * y1);
        }

        double ans = Math.abs(total) / 2.0;
        System.out.printf("%.1f", ans);
        br.close();
    }
}
