import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, K;
    static int[] pos;
    // -X, +X 범위 내에서 K번으로 모든 점 커버 가능한지
    static boolean can(int X){
        int idx = 0;
        int cnt = 0;
        while(idx < N){
            cnt++;

            int end = pos[idx] + 2*X;

            while(idx < N && pos[idx] <= end){
                idx++;
            }
        }
        return cnt <= K;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        pos = new int[N];
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = 0; i<N; i++){
            pos[i] = read();
            min = Math.min(min, pos[i]);
            max = Math.max(max, pos[i]);
        }
        Arrays.sort(pos);

        int l = 0;
        int r = max-min;
        int ans = 0;
        while(l <= r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        System.out.print(ans);
    }
}