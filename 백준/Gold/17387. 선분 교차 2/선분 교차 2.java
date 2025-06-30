
import java.util.*;
import java.io.*;
public class Main {
    // CCW를 이용한 두 선분 교차 판정
    private static boolean isIntersect(int[] s1, int[] s2) {
        int x1 = s1[0], y1 = s1[1], x2 = s1[2], y2 = s1[3];
        int x3 = s2[0], y3 = s2[1], x4 = s2[2], y4 = s2[3];

        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // 일반적인 교차 여부
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
            return true;
        }

        // 일직선 상에 있는 경우 추가 판정
        return (ccw1 == 0 && isOverlap(x1, y1, x2, y2, x3, y3)) ||
                (ccw2 == 0 && isOverlap(x1, y1, x2, y2, x4, y4)) ||
                (ccw3 == 0 && isOverlap(x3, y3, x4, y4, x1, y1)) ||
                (ccw4 == 0 && isOverlap(x3, y3, x4, y4, x2, y2));
    }

    // CCW 계산
    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long crossProduct = (long) (x2 - x1) * (y3 - y1) - (long) (y2 - y1) * (x3 - x1);
        return Long.compare(crossProduct, 0); // > 0: 반시계, < 0: 시계, == 0: 일직선
    }

    // 선분이 겹치는지 확인
    private static boolean isOverlap(int x1, int y1, int x2, int y2, int x3, int y3) {
        if (x1 > x2) { // 정렬
            int temp = x1; x1 = x2; x2 = temp;
            temp = y1; y1 = y2; y2 = temp;
        }
        if (x3 > x1 && x3 > x2) return false;
        return Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s1 = new int[4];
        int[] s2 = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++){
            s1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++){
            s2[i] = Integer.parseInt(st.nextToken());
        }

        if(isIntersect(s1,s2))
            bw.write("1");
        else
            bw.write("0");
        bw.flush();
        bw.close();
        br.close();
    }
}
