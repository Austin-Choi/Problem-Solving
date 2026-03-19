/*
일단 주어진건 연결 그래프, 트리 형태임
무방향이니까 degree 세면 연결된 가지수 알수 있음
straight 체인 조건은 deg == 2임
가지 연결수 -2 한거 다 더하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] deg;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        deg = new int[N+1];
        for(int i = 0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            deg[a]++;
            deg[b]++;
        }
        int tot = 0;
        for(int i = 1; i<=N; i++){
            tot += Math.max(0, deg[i]-2);
        }
        System.out.print(tot);
    }
}