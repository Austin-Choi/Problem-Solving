import java.util.*;
import java.io.*;

/*
이분탐색은 소수점 4째자리까지 반올림해서 출력하라니까 
오차 고려해서 실수 단위 탐색해야함
-> 반복 100
can 함수
모두가 최대 (double) X초를 사용해서 같은 위치에 도달 가능한지 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] pos;
    static int[] speed;

    static boolean can(double X){
        double s = Double.NEGATIVE_INFINITY;
        double e = Double.POSITIVE_INFINITY;
        for(int i = 0; i<N; i++){
            s = Math.max(s, (double)pos[i] - (double)speed[i] * X);
            e = Math.min(e, (double)pos[i] + (double)speed[i] * X);
        }
        return s <= e;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        pos = new int[N];
        for(int i = 0; i<N; i++)
            pos[i] = read();
        speed = new int[N];
        for(int i = 0; i<N; i++)
            speed[i] = read();
        
        double l = 0;
        double r = 200_000_000.0;
        double ans = 0.0;
        for(int i = 0; i<100; i++){
            double mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid;
            }
            else
                l = mid;
        }
        System.out.printf("%.4f", ans);
    }
}