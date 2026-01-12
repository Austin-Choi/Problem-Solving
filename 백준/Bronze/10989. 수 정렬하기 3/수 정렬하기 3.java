import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        short[] arr = new short[N];
        for(int i = 0; i<N; i++){
            arr[i] = Short.parseShort(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr);
        for(short s : arr){
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}