/*
l = 0, r = 1e9으로 두고
합산 사용량 먼저 찾고 -> 이걸 mid
cost(mid) == A를 만족해야함

l = 0, r = mid라 하고
찾으려는 한쪽 사용량을 x
합해서 mid가 되야하니까
x , mid-x로 나타냄
|cost(x) - cost(mid-x)| == B를 만족하는 x와 total-x중에 작은게 답
--
if(x>1 000 000)
1 000 000 뺀거에 거기다가 7곱하기
x = 1 000 000

if(x > 10 000)
10 000 뺀거에 5 곱하기
x = 10 000
if(x>100)
100 뺀거에 3 곱하기
x = 100

x 에 2 곱하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static long A,B;
    static long calcCost(long mid){
        long cost = 0;
        if(mid > 1_000_000L){
            cost += 7L* (mid-1_000_000);
            mid = 1_000_000L;
        }
        if(mid > 10_000L){
            cost += 5L* (mid-10_000);
            mid = 10_000L;
        }
        if(mid > 100L){
            cost += 3L* (mid-100);
            mid = 100L;
        }
        cost += mid * 2L;
        return cost;
    }

    static long bs(long cost){
        long l = 0;
        long r = 1_000_000_000L;
        while(l<=r){
            long mid = (l+r)/2;
            long c = calcCost(mid);
            if(c==cost)
                return mid;
            else if(c > cost)
                r = mid -1;
            else
                l = mid + 1;
        }
        return -1;
    }

    static long bsDiff(long total, long target){
        long l = 0L;
        long r = total;
        while(l<=r){
            long mid = (l+r)/2;
            long d = calcCost(mid) - calcCost(total-mid);
            if(d == target)
                return mid;
            if(d > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            if(A == 0 && B == 0)
                break;

            long total = bs(A);
            long x = bsDiff(total, B);
            long ans = Math.min(x, total - x);
            sb.append(calcCost(ans)).append('\n');
        }
        System.out.println(sb);
    }
}
