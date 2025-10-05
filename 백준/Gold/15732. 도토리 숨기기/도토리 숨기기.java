/*
시작에서 C개씩해서 도달하는 것
100 110 120 130 140 150 -> 140
110 125 130 145 -> 125

a,b,c가 주어질때
a,b를 보고 (D-1)*c보다 b가 작으면 b가 new max, a는 new min

도토리(D)를 다 넣을수 있을때 마지막 박스 번호(mid)를 리턴?
(min(b,mid) - a / c) +1
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N,K,D;
    static class Rule{
        int a;
        int b;
        int c;
        Rule(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static Rule[] rules;
    static boolean can(long x){
        long rst = 0;
        for(Rule r : rules){
            if(r.a > x)
                continue;
            rst += (Math.min(r.b, x) - r.a) / r.c + 1;
            if(rst >= D)
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        rules = new Rule[K];
        for(int k = 0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            rules[k] = new Rule(a,b,c);
        }
        long l = 0;
        long r = (long) N;
        long ans = 0;
        while(l<=r){
            long mid = (l+r)/2;
            // 가능 -> 끝번호 줄여보기
            if(can(mid)){
                ans = mid;
                r = mid -1;
            }
            // 불가능 -> 끝번호 더 키우기
            else{
                l = mid + 1;
            }
        }
        System.out.println(ans);
    }
}
