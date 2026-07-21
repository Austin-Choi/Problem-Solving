import java.util.*;
import java.io.*;

/*
dp
dp[i] = i날에 사서 파는 최대이익.?

그리디로 하면 ..
뒤에서부터 보면 뒤는 항상 최고 가격임 
현재에서 삿다고 하고 최고 가격에 팔았을때 이득 갱신하기?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = read();
        }

        int rmax = 0;
        int ans = 0;
        for(int i = N-1; i>=0; i--){
            rmax = Math.max(rmax, arr[i]);
            ans = Math.max(ans, rmax - arr[i]);
        }
        System.out.print(ans);
    }
}