import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] phones;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ans = 0;
        phones = new int[10];
        int prevPhone = -1;
        for(int i =0; i<N; i++){
            int idx = Integer.parseInt(st.nextToken());
            
            int cur = phones[idx];
            if(idx == prevPhone)
                cur *= 2;
            else 
                cur = 2;
            
            if(ans + cur >= 100){
                ans = 0;
                phones = new int[10];
                prevPhone = -1;
                continue;
            }
            
            ans += cur;
            prevPhone = idx;
            phones[idx] = cur;
        }

        System.out.println(ans);
    }
}