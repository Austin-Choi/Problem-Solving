import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int out = (int) Math.round(n*(0.15));
        Arrays.sort(arr);
        double tot = 0.0;
        for(int i = out; i<n-out; i++){
            tot += arr[i];
        }
        System.out.print(Math.round(tot/(n-2*out)));
    }
}