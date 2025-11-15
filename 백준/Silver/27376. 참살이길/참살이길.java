import java.util.*;
import java.io.*;
public class Main{
    static class Light implements Comparable<Light>{
        long pos;
        long t;
        long s;
        Light(long p, long t, long s){
            this.pos = p;
            this.t = t;
            this.s = s;
        }
        @Override
        public int compareTo(Light o){
            return Long.compare(this.pos, o.pos);
        }
    }
    static Light[] ls;
    static long calc(long cur, int idx){
        long s = ls[idx].s;
        long t = ls[idx].t;
        // s되기전 빨강임
        if(cur < s)
            return s-cur;
        cur -= s;
        // 0기준이 초록으로 시작함
        // cur/t = 1,3,5, 이거 다 빨강임
        // 이때 t - cur%t 만큼 기다려야됨
        if(cur / t % 2 == 0)
            return 0;
        else
            return  t - cur % t;
    }

    public static void main(String[] args) throws IOException{
        int n,k;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ls = new Light[k];
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b= Long.parseLong(st.nextToken());
            long c= Long.parseLong(st.nextToken());
            ls[i] = new Light(a,b,c);
        }
        Arrays.sort(ls);

        long ans = 0;
        long prev = 0;
        for(int i = 0; i<k; i++){
            ans += ls[i].pos - prev;
            ans += calc(ans, i);
            prev = ls[i].pos;
        }
        ans += n - prev;
        System.out.println(ans);
    }
}
