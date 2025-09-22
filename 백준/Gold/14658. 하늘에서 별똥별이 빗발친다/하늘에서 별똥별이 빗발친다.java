/*
모든 x,y조합으로 트램펄린 시작 꼭지점 만들고
전체 리스트 순회해서 몇개나 들어가는지 세기
K가 100이라서 100^3 가능
 */
import java.awt.Point;
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,L,K;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Point[] pl = new Point[K];
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int ci = Integer.parseInt(st.nextToken());
            int cj = Integer.parseInt(st.nextToken());
            pl[i] = new Point(ci,cj);
        }
        for(int i = 0; i<K; i++){
            int ci = pl[i].x;
            for(int j = 0; j<K; j++){
                int cj = pl[j].y;
                int ni = ci+L;
                int nj = cj+L;
                int count = 0;
                for(int k = 0; k<K; k++){
                    int ii = pl[k].x;
                    int jj = pl[k].y;
                    if(ci <= ii && ii <= ni && cj <= jj && jj <= nj)
                        count++;
                }
                ans = Math.max(ans, count);
            }
        }
        ans = K-ans;
        System.out.println(ans);
    }
}
