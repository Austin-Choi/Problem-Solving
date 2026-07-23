import java.util.*;
import java.io.*;



public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        boolean prev = true;
        int ans = 0;
        for(int i = 0; i<N; i++){
            if(A[i] != B[i]){
                if(prev){
                    prev = false;
                    ans++;
                }
            }
            else
                prev = true;
        }
        System.out.print(ans);
    }
}