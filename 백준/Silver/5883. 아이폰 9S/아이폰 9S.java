import java.util.*;
import java.io.*;
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Set<Integer> s = new HashSet<>();
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            s.add(arr[i]);
        }

        int ans = 0;
        for(int r : s){
            int maxSeq = 0;
            int cnt = 0;
            int prev = -1;
            for(int x : arr){
                if(x == r)
                    continue;
                if(x == prev)
                    cnt++;
                else 
                    cnt = 1;
                prev = x;
                maxSeq = Math.max(maxSeq, cnt);
            }
            ans = Math.max(ans, maxSeq);
        }
        System.out.println(ans);
    }
}