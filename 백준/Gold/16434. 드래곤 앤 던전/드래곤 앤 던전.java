import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long hatk;
    static class Info{
        long type;
        long a;
        long h;
        Info(long t, long a, long h){
            this.type = t;
            this.a = a;
            this.h = h;
        }
    }

    static boolean can(Info[] arr, long x){
        long maxH = x;
        long curH = x;
        long curA = hatk;
        for(int i = 0; i<N; i++){
            long roomType = arr[i].type;
            long roomA = arr[i].a;
            long roomH = arr[i].h;
            if(roomType == 1){
                long hitTimes = 0;
                hitTimes = roomH / curA;
                if(roomH % curA != 0)
                    hitTimes += 1;
                long lostH = (hitTimes-1 )* roomA;

                if(curH <= lostH)
                    return false;
                curH -= lostH;
            }
            else{
                if(curH + roomH >= maxH)
                    curH = maxH;
                else
                    curH += roomH;
                curA += roomA;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        hatk = Long.parseLong(st.nextToken());
        Info[] arr = new Info[N];
        long l = 0;
        long r = Long.MAX_VALUE;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            arr[i] = new Info(t,a,h);
        }

        long ans = 0;
        while(l<=r){
            long mid = (l+r) / 2;
            if(can(arr, mid)) {
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }

        System.out.println(ans);
    }
}
