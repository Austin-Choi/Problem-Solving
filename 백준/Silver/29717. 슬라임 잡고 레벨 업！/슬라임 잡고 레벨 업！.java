/*
총 경험치 -> 1~N의 합
n*(n+1) * 1/2

x*(x+1) >= N*(N+1) * 1/2
를 만족하는 최소 x값 이분탐색으로 찾기
 */
import java.io.*;
public class Main {
    static int T;
    static long N;

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            N = Long.parseLong(br.readLine());
            long a = N*(N+1)/2;
            long l = 0;
            long r = 1000000000;
            long ans = -1;
            while(l<=r){
                long mid = (l+r)/2;
                if(mid*(mid+1) <= a){
                    ans = mid;
                    l = mid +1;
                }
                else
                    r = mid -1;
            }
            sb.append(ans+1).append("\n");
        }
        System.out.println(sb);
    }
}