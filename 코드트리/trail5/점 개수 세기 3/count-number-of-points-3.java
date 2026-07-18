import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,Q;
    static int[] arr;

    // p 이상인 첫 위치
    static int lb(int p){
        int ans = N;
        int l = 0;
        int r = N-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(arr[mid] >= p){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        return ans;
    }

    // p 초과인 첫 위치
    static int ub(int p){
        int ans = N;
        int l = 0;
        int r = N-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(arr[mid] > p){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        Q = read();
        arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = read();
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();
            int low = lb(a);
            int high = ub(b);
            sb.append(high - low).append("\n");
        }
        System.out.print(sb);
    }
}