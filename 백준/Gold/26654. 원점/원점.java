/*
넓이 B인 원에 최대한 많은 꼭지점을 위치시킨다했으면 변동이 가능하다는거고
수식으로 딱나온다는게 아니니까 탐색을 할텐데
T가 10000이고 N의 최대 범위가 백만이니까 이분탐색 조져서 찾아내면 될거같은데
-> 외접원 반지름

A = 1/2*N*(R^2)*sin(2PI / N)
-> 2A = N R^2 *sin(2Pi/N)
-> R = sqrt(2A/(N*sin(2PI/N))
B = r^2 * PI
연속된 K개 꼭지점 중심각(k개 분포도) = 2PI/N * (k-1)
중심각이 180도 넘어가면 최소 반지름 = 외접반지름
이하면 -> R*sin(th/2)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T;
    static final double pi = Math.PI;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            double A = Double.parseDouble(st.nextToken());
            double B = Double.parseDouble(st.nextToken());

            double r2 = B/pi;
            double R2 = 2*A / (N*Math.sin(2*pi/N));

            // 오차보정
            if(r2 + 1e-12>= R2){
                sb.append(N).append("\n");
                continue;
            }

            int l = 1;
            int r = N;
            int ans = 1;

            while(l<=r){
                int mid = (l+r)/2;
                double need;
                if(2L *(mid-1) <= N){
                    double s = Math.sin(pi*(mid-1)/N);
                    need = R2*s*s;
                }
                else
                    need = R2;

                if(r2 >= need){
                    ans = mid;
                    l = mid+1;
                }
                else
                    r = mid-1;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
