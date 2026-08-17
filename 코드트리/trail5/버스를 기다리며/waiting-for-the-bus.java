import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,C;
    static int[] time;
    // 모든 사람이 버스를 기다린 시간이 X초 이하인지
    static boolean can(int X){
        int max = 0;
        int idx = 0;
        // 모든 M개의 버스에 대하여
        for(int b = 0; b<M; b++){
            if(idx >= N)
                return true;
            // 아직 버스 안탄 첫번째 사람
            int start = time[idx];
            // 해당 버스 출발 시간
            int end = start + X;

            int cnt = 0;
            while(idx<N && cnt<C && time[idx] <= end){
                idx++;
                cnt++;
            }
        }
        return idx == N;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        C = read();
        time = new int[N];
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = 0; i<N; i++){
            time[i] = read();
            min = Math.min(min, time[i]);
            max = Math.max(max, time[i]);
        }
        Arrays.sort(time);

        int l = 0;
        int r = max-min;
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid + 1;
        }
        System.out.print(ans);
    }
}