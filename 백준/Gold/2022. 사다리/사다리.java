/*
사다리가 교차되어서 빌드 사이에 기대져 있으면
땅에서부터 c인 지점에서 정확히 교차하는데
? 거리가 빌딩 사이의 거리 ? 구하기
? 를 mid로 하고
0 ~ min(x,y)까지임

결정함수
xh = sqrt(x^2-w^2)
yh = sqrt(y^2-w^2)
양쪽 높이에 대해서 직각삼각형이 닮은꼴이라
전체 높이와 교차 높이에 대한 비율이 서로 같아야함
c` / xh + c` / yh = 1
이 식을 c에 대한 식으로 정리하면
xh * yh / (xh + yh) = c
이게 mid를 충족시킬 수 있는지를 리턴

실수 기반 이분탐색
l <= r같은건 정수일때
실수는 오차범위로 기준을 둬야 함
 */

import java.util.*;
import java.io.*;
public class Main {
    static double x,y,c;
    static boolean can(double mid){
        double xh = Math.sqrt(Math.pow(x,2)-Math.pow(mid,2));
        double yh = Math.sqrt(Math.pow(y,2)-Math.pow(mid,2));
        return (xh*yh / (xh+yh)) >= c;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double l = 0.0;
        double r = Math.min(x,y);
        while(r - l > 1e-4){
            double mid = (l+r)/2;
            if(can(mid)){
                l = mid;
            }
            else
                r = mid;
        }

        System.out.printf("%.3f", l);
    }
}
