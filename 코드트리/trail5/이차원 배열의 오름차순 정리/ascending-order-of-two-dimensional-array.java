import java.util.*;
import java.io.*;

/*
i*j 이하의 원소가 K개 이상 있는지
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, K;

    static boolean can(long X){
        long sum = 0;
        for(int i =1; i<=N; i++){
            long j = X/i;
            sum += Math.min(N, j);
            if(sum >= K)
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();

        long l = 1;
        long r =(long) N*N;
        long ans = 0;
        while(l <= r){
            long mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid +1;
        }
        System.out.print(ans);
    }
}