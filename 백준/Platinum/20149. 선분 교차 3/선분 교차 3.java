import java.util.*;
import java.io.*;
public class Main {
    // ccw = 0 -> 세 점이 한 직선 위에 있음
    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3){
        return Long.compare((long) (x2-x1) * (y3-y1) -  (long) (y2-y1)*(x3-x1), 0);
    }

    // 선분 위에 x,y가 있는지
    static boolean isOnLine(int x1, int y1, int x2, int y2, int x, int y){
        return Math.min(x1,x2) <= x && x <= Math.max(x1,x2)
                && Math.min(y1,y2) <= y && y <= Math.max(y1,y2);
    }

    // 선분 A와 B가 끝점을 포함해서 구간 공유 여부판단
    static boolean isOverlap(int[] a, int[] b){
        return Math.max(Math.min(a[0], a[2]), Math.min(b[0], b[2])) <= Math.min(Math.max(a[0],a[2]), Math.max(b[0], b[2]))
                && Math.max(Math.min(a[1], a[3]), Math.min(b[1], b[3])) <= Math.min(Math.max(a[1],a[3]), Math.max(b[1], b[3]));
    }
    /*
       1) 내부에서 한점이 교차
         (y2 − y1)x − (x2 − x1)y + (x2 − x1)y1 − (y2 − y1)x1 = 0
         (y2 - y1)*(x-x1) + (x2-x1)*(y1-y)
         (y2-y1)*x + (x1-x2)*y = (y2-y1)*x1 +(x1-x2)*y1

         y2-y1 = A
         x1-x2 = B

         A*x + B*y = A*x1 + B*y1
         A1*x + B1*y = C1
         A2*x + B2*y = C2

         크래머공식
         det = A1*B2 - A2*B1
         x = (C1*B2 - B1*C3) / det
         y = (A1*C2 - C1*A2) / det
     */
    static void printInternalCrossPoint(int[] a, int[] b){
        System.out.println(1);

        double x1 = a[0], y1 = a[1];
        double x2 = a[2], y2 = a[3];
        double x3 = b[0], y3 = b[1];
        double x4 = b[2], y4 = b[3];

        double A1 = y2 - y1;
        double B1 = x1 - x2;
        double C1 = A1 * x1 + B1 * y1;

        double A2 = y4 - y3;
        double B2 = x3 - x4;
        double C2 = A2 * x3 + B2 * y3;

        double det = A1 * B2 - A2 * B1;

        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;

        if (Math.abs(x) < 1e-9) x = 0;
        if (Math.abs(y) < 1e-9) y = 0;

        // 3.0 3.0 정수로 출력하기
        if(x == (long)x && y == (long)y)
            System.out.println((long)x + " "+(long)y);
        else
            System.out.println(x+" "+y);
    }
    static void print(long a, long b){
        System.out.print("1\n" + a + " " + b);
    }
    // 1155 5599
    static void printEndPoint(int[] ccws, int[] a, int[] b){
        if(ccws[0]==0 && ccws[1]==0 && ccws[2]==0 && ccws[3]==0){
            if(!isOverlap(a, b)) {
                System.out.println(0);
                return;
            }

            // 교집합 구간 최소, 최대 좌표
            int lx = Math.max(Math.min(a[0], a[2]), Math.min(b[0], b[2]));
            int rx = Math.min(Math.max(a[0], a[2]), Math.max(b[0], b[2]));
            int ly = Math.max(Math.min(a[1], a[3]), Math.min(b[1], b[3]));
            int ry = Math.min(Math.max(a[1], a[3]), Math.max(b[1], b[3]));

            // 한점만
            if(lx == rx && ly == ry)
                print(lx, ly);
            // 구간이 존재함 -> 여러점 겹침
            else
                System.out.println(1);
            return;
        }
        // 완전 공선이 아닐때, 끝점만 교차하는거 있는지 보기
        if(ccws[0] == 0 && isOnLine(a[0],a[1],a[2],a[3],b[0],b[1]))
            print(b[0],b[1]);
        else if(ccws[1] == 0 && isOnLine(a[0],a[1],a[2],a[3],b[2],b[3]))
            print(b[2],b[3]);
        else if(ccws[2] == 0 && isOnLine(b[0],b[1],b[2],b[3],a[0],a[1]))
            print(a[0],a[1]);
        else if(ccws[3] == 0 && isOnLine(b[0],b[1],b[2],b[3],a[2],a[3]))
            print(a[2],a[3]);
        else
            System.out.println(0);
    }

    static void sol(int[] a, int[] b){
        int[] ccws = new int[4];
        ccws[0] = ccw(a[0],a[1],a[2],a[3],b[0],b[1]);
        ccws[1] = ccw(a[0],a[1],a[2],a[3],b[2],b[3]);
        ccws[2] = ccw(b[0],b[1],b[2],b[3],a[0],a[1]);
        ccws[3] = ccw(b[0],b[1],b[2],b[3],a[2],a[3]);

        if(ccws[0] * ccws[1] <=0 && ccws[2] * ccws[3] <=0){
            if(ccws[0] * ccws[1] < 0 && ccws[2] * ccws[3] < 0)
                printInternalCrossPoint(a,b);
            else
                printEndPoint(ccws, a, b);
        }
        else if(ccws[0] == 0 && ccws[1] == 0 && ccws[2] == 0 && ccws[3] == 0)
            printEndPoint(ccws, a, b);
        else
            System.out.println(0);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] s1 = new int[4];
        int[] s2 = new int[4];
        for(int i = 0; i<4; i++){
            s1[i] = Integer.parseInt(st.nextToken());
            s2[i] = Integer.parseInt(st2.nextToken());
        }
        sol(s1, s2);
    }
}
