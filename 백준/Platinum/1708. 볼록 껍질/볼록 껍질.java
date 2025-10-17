/*
ccw를 써서 방향을 항상 같게 유지하고
평행이 나오면 더 먼 점을 택해야 함
반복.. 느림

모노톤 체인 알고리즘
중복뺘고 i,j 정렬
왼->오 ccw>0 아래반쪽
오->왼 ccw>0 위반쪽
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static class Point implements Comparable<Point>{
        long x;
        long y;
        Point(long x, long y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o){
            if(this.x == o.x)
                return Long.compare(this.y, o.y);
            return Long.compare(this.x, o.x);
        }
    }
    static Point[] pts;
    static long ccw(long ai, long aj, long bi, long bj, long ci, long cj){
        long abi = bi - ai;
        long abj = bj - aj;
        long aci = ci - ai;
        long acj = cj - aj;
        return abi * acj - abj * aci;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pts = new Point[N];
        StringTokenizer st;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            pts[i] = new Point(n,m);
        }

        Arrays.sort(pts);

        ArrayList<Point> pl = new ArrayList<>();
        Point prev = null;
        for(int i = 0; i<N; i++){
            if(prev == null || !(pts[i].x == prev.x && pts[i].y == prev.y)){
                pl.add(pts[i]);
            }
            prev = pts[i];
        }
        N = pl.size();

        ArrayList<Point> lower = new ArrayList<>();
        for(int i = 0; i<N; i++){
            Point p = pl.get(i);
            while(lower.size() >= 2){
                Point b = lower.get(lower.size()-1);
                Point a = lower.get(lower.size()-2);
                if(ccw(a.x, a.y, b.x, b.y, p.x, p.y) <= 0)
                    lower.remove(lower.size()-1);
                else
                    break;
            }
            lower.add(p);
        }

        ArrayList<Point> upper = new ArrayList<>();
        for(int i = N-1; i>=0; i--){
            Point p = pl.get(i);
            while(upper.size() >= 2){
                Point b = upper.get(upper.size()-1);
                Point a = upper.get(upper.size()-2);
                if(ccw(a.x, a.y, b.x, b.y, p.x, p.y) <= 0)
                    upper.remove(upper.size()-1);
                else
                    break;
            }
            upper.add(p);
        }

        int ans = lower.size() + upper.size();
        if(ans <= 2)
            System.out.println(ans);
        else
            System.out.println(ans -2);
    }
}
