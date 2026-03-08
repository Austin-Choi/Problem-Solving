import java.io.*;
public class Main {
    static int N;
    static boolean can(long x){
        long rst = (x+1)*x * 3 + 1;
        if(rst >= N)
            return true;
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long l = 0;
        long r = N;
        long ans = 0;
        while(l<=r){
            long mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        System.out.print(ans+1);
    }
}
