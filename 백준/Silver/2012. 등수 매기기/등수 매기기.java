
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        arr[0] = 0;

        for(int i = 1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long sum = 0;
        for(int i = 1; i<=N; i++){
            sum += Math.abs(i - arr[i]);
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();

    }
}
