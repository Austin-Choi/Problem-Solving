import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int min = 1_000_001;
        int max = -1_000_001;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            int cur = Integer.parseInt(st.nextToken());
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }
        System.out.print(min+" "+max);
    }
}