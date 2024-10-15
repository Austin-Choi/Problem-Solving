import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

  static long L, ans;
  static long cx, cy;
  static int cd;
  static int N;
  static ArrayList<Line> al = new ArrayList<>();
  static int[] dx = { 1, 0, -1, 0 };
  static int[] dy = { 0, -1, 0, 1 };

  static class Line {
    private long x1, y1, x2, y2;
    private String dir;

    public Line(long x1, long y1, long x2, long y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      if (x1 == x2) {
        dir = "vertical";  // 수직 선분
      } else {
        dir = "horizontal";  // 수평 선분
      }
      lineSort();
    }

    // x1, y1이 항상 x2, y2보다 작도록 정렬
    public void lineSort() {
      if (this.x2 < this.x1) {
        long tmp = this.x2;
        this.x2 = this.x1;
        this.x1 = tmp;
      }
      if (this.y2 < this.y1) {
        long tmp = this.y2;
        this.y2 = this.y1;
        this.y1 = tmp;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    L = Integer.parseInt(br.readLine());
    N = Integer.parseInt(br.readLine());
    cx = cy = 0;

    // 경계선 추가 (뱀의 경계를 설정)
    al.add(new Line(-L - 1, L + 1, L + 1, L + 1));
    al.add(new Line(-L - 1, -L - 1, L + 1, -L - 1));
    al.add(new Line(-L - 1, -L - 1, -L - 1, L + 1));
    al.add(new Line(L + 1, -L - 1, L + 1, L + 1));

    long count;
    String dir;
    for (int t = 0; t <= N; t++) {
      if (t == N) {
        count = Long.MAX_VALUE;
        dir = "L";
      } else {
        StringTokenizer st = new StringTokenizer(br.readLine());
        count = Long.parseLong(st.nextToken());
        dir = st.nextToken();
      }

      long m = Long.MAX_VALUE;
      for (Line line : al) {
        // 수평 선분인 경우
        if (line.dir.equals("horizontal")) {
          if (cd == 0 && cy == line.y1 && cx < line.x1) {
            m = Math.min(m, line.x1 - cx);
          } else if (cd == 2 && cy == line.y1 && cx > line.x2) {
            m = Math.min(m, cx - line.x2);
          } else if (cd == 1 && line.x1 <= cx && cx <= line.x2 && cy > line.y1) {
            m = Math.min(m, cy - line.y1);
          } else if (cd == 3 && line.x1 <= cx && cx <= line.x2 && cy < line.y1) {
            m = Math.min(m, line.y1 - cy);
          }
        } 
        // 수직 선분인 경우
        else {
          if (cd == 0 && line.y1 <= cy && cy <= line.y2 && cx < line.x1) {
            m = Math.min(m, line.x1 - cx);
          } else if (cd == 2 && line.y1 <= cy && cy <= line.y2 && cx > line.x1) {
            m = Math.min(m, cx - line.x1);
          } else if (cd == 1 && cx == line.x1 && cy > line.y2) {
            m = Math.min(m, cy - line.y2);
          } else if (cd == 3 && cx == line.x1 && cy < line.y1) {
            m = Math.min(m, line.y1 - cy);
          }
        }
      }

      if (count < m) {
        // 충돌하지 않는 경우 주어진 거리만큼 이동
        al.add(new Line(cx, cy, cx + dx[cd] * count, cy + dy[cd] * count));
        cx += dx[cd] * count;
        cy += dy[cd] * count;
        ans += count;

        // 회전 처리
        if (dir.equals("L")) {
          cd = (cd + 3) % 4;  // 왼쪽 회전
        } else {
          cd = (cd + 1) % 4;  // 오른쪽 회전
        }
      } else {
        // 충돌할 경우 가능한 최대 거리만큼 이동 후 종료
        ans += m;
        break;
      }
    }
    br.close();
    System.out.println(ans);
  }
}
