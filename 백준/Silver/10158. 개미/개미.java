/*
시간범위가 너무 커서 bfs같이 시뮬레이션은 못함 시간이 0.15초임
주어진 보드 안에서 어딘가에서 출발점을 놓으면 시간이 얼마나 걸릴지는 몰라도
필연적으로 같은 위치에 돌아오게 되어있음
근데 그냥 위치로 하면 경로를 다 돌기 전에 거쳐가는 경우도 있으므로 이렇게 풀려면 이걸 고려해야함
---------------
그냥 사각형을 반사로 펼친 평면 위에서 직선 이동이고 원래 사각형으로 접어 넣을때 매핑됨
 */
import java.util.*;
import java.io.*;
public class Main {
    static long calc(long pos, long n){
        long m = pos % (2*n);
        if(m<=n)
            return m;
        return 2*n - m;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long w = Long.parseLong(st.nextToken());
        long h = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long p = Long.parseLong(st.nextToken());
        long q = Long.parseLong(st.nextToken());
        long t = Long.parseLong(br.readLine());
        System.out.println(calc(p+t,w)+" "+calc(q+t,h));
    }
}
