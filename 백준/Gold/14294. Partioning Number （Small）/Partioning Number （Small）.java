
import java.util.*;
import java.io.*;
public class Main{
    static int T;
    static String format(int num, long ans){
        return "Case #"+num+": "+ans+"\n";
    }

    static long sol(int n, int d){
        long ans = 0;
        for(int t = 1; t*d <= n; t++){
            int k = t*d;

            for(int m = 1; m*k <= n; m++){
                int plus = n - m*k;
                if(plus > 2*m)
                    continue;

                for(int c = 0; c*2 <= plus; c++){
                    int b = plus - 2*c;
                    if(b + c <= m - 1)
                        ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            sb.append(format(t, sol(n,d)));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
