/*
출발점, 도착점을 내부 영역에 포함하고 있는 원이 몇개인지 세기
-> 조건에 행성 경계가 맞닿거나 서로 교차하는 경우는 없다했으니

각 원의 중심과 타겟 점의 거리가 반지름 미만이면 내부에 있는 것

각 원의 정보당 시작, 끝점을 넣어봄
cnt라 하고
1) 둘 다 그 안에 있으면 +=0
2) 둘 다 그거 밖에 있으면 +=0
3) 한쪽만 그거 안에 있으면 +=1
 */
import java.util.*;
import java.io.*;
public class Main {
    static boolean isIn(int ci, int cj, int ti, int tj, int cr){
        int dist = (ci-ti)*(ci-ti) + (cj-tj)*(cj-tj);
        return dist < cr*cr;
    }
    static int T;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] t = new int[4];
            for(int i = 0; i<4; i++)
                t[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(br.readLine());
            int cnt = 0;
            while(n-->0){
                st = new StringTokenizer(br.readLine());
                int ci = Integer.parseInt(st.nextToken());
                int cj = Integer.parseInt(st.nextToken());
                int cr = Integer.parseInt(st.nextToken());
                boolean a = isIn(ci,cj,t[0],t[1],cr);
                boolean b = isIn(ci,cj,t[2],t[3],cr);
                if(a^b)
                    cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}
