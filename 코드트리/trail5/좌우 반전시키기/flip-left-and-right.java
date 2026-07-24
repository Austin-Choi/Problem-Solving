import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] arr;

    static void flip(int idx){
        for(int i = Math.max(idx-1,0); i<=Math.min(idx+1, N-1); i++){
            arr[i] = 1-arr[i];
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = read();
        }

        int ans = 0;
        for(int i =1; i<N; i++){
            if(arr[i-1] == 0){
                flip(i);
                ans++;
            }
        }
        System.out.print(arr[N-1] == 0 ? -1 : ans);
    }
}