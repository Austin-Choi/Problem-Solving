/*
ccw(A,B,C)
점 C가 선분 AB 기준 어느쪽
양수 -> 왼쪽, 음수 -> 오른쪽, 0 -> 일직선

선분이 서로가 서로를 가로지를때만 교차이므로
ccw1*2 < 0 -> C와 D는 A-B의 서로 반대편에 있음 -> 선분 CD는 AB를 가로지를 수도 있음
ccw3*4 < 0 -> A와 B는 C-D의 서로 반대편에 있음 -> 선분 AB도 CD를 가로지름
 */
import java.util.*;
import java.io.*;
public class Main {
    static long ccw(long ai, long aj, long bi, long bj, long ci, long cj){
        long abi = bi - ai;
        long abj = bj - aj;
        long aci = ci - ai;
        long acj = cj - aj;
        return abi*acj - abj*aci;
    }
    static boolean isIntersect(long ai, long aj, long bi, long bj, long ci, long cj, long di, long dj){
        long ccw1 = ccw(ai,aj,bi,bj,ci,cj);
        long ccw2 = ccw(ai,aj,bi,bj,di,dj);
        long ccw3 = ccw(ci,cj, di,dj, ai,aj);
        long ccw4 = ccw(ci,cj,di,dj,bi,bj);

        // long*long 오버플로우
        boolean c1 = (ccw1 > 0 && ccw2 < 0) || (ccw1<0 && ccw2>0);
        boolean c2 = (ccw3 >0 && ccw4 < 0) || (ccw3 < 0 && ccw4>0);

        return c1 && c2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[4];
        for(int i = 0; i<4; i++)
            a[i] = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] b = new long[4];
        for(int i = 0; i<4; i++)
            b[i] = Long.parseLong(st.nextToken());
        if(isIntersect(a[0],a[1],a[2],a[3],b[0],b[1],b[2],b[3]))
            System.out.print(1);
        else
            System.out.print(0);
    }
}
