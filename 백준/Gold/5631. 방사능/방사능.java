/*
그냥 NQ하니까 시간초과 메모리초과 남..
N = oneA + two + oneB + etc
= (A+B) - two + two + etc
= (A+B) + etc
-> etc = N - (A+B)
...
집 하나에 대해
중심까지의 거리를 sqrt안하고 구해놓음
그리고 그 거리배열에 대해서 r1제곱한걸 이분탐색으로
rr이하의 최대 인덱스를 찾음
그러면 그게 곧 갯수가 됨.
그렇게 a,b 구하고 N-a-b해주면 답
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int upperBound(long[] arr, long t){
        int l = 0, r = arr.length;
        while(l<r){
            int mid = (l+r)/2;
            if(arr[mid] <= t)
                l = mid+1;
            else
                r = mid;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int c = 1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;

            int[] x = new int[N];
            int[] y = new int[N];
            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            long[] distA = new long[N];
            long[] distB = new long[N];

            for(int i = 0; i<N; i++){
                long dx = x[i] - ax;
                long dy = y[i] - ay;
                distA[i] = dx*dx + dy*dy;

                dx = x[i] - bx;
                dy = y[i] - by;
                distB[i] = dx*dx + dy*dy;
            }

            Arrays.sort(distA);
            Arrays.sort(distB);

            sb.append("Case ").append(c++).append(":\n");

            for(int i = 0; i<q; i++){
                st = new StringTokenizer(br.readLine());
                long r1 = Long.parseLong(st.nextToken());
                long r2 = Long.parseLong(st.nextToken());

                long r1sq = r1*r1;
                long r2sq = r2*r2;

                int a = upperBound(distA, r1sq);
                int b = upperBound(distB, r2sq);
                sb.append(Math.max(0, N-a-b)).append("\n");
            }
        }
        System.out.print(sb);
    }
}