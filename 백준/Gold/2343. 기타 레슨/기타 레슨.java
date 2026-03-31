import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[] t;
    // X분짜리 최대 M개로 구성 가능?
    static boolean can(int X){
        int cnt = 0;
        int tot = 0;
        for(int i = 0; i<N; i++){
            if(tot + t[i] <= X){
                tot += t[i];
            }
            else {
                tot = t[i];
                cnt++;
            }
            if(cnt >= M)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = new int[N];
        st = new StringTokenizer(br.readLine());
        /*
        최소는 가장 긴 강의,
        최대는 강의 전체 합친거
         */
        int l = 0;
        int r = 0;
        for(int i = 0; i<N; i++){
            t[i] = Integer.parseInt(st.nextToken());
            l = Math.max(l, t[i]);
            r += t[i];
        }
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        System.out.print(ans);
    }
}