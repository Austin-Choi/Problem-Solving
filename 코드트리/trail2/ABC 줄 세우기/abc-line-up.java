import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        char[] A = s.replaceAll(" ","").toCharArray();
        int ans = 0;
        for(int i= 0;i<N; i++){
            for(int j = i+1; j<N; j++){
                if(A[i] > A[j]){
                    ans++;
                }
            }
        }
        System.out.print(ans);
    }
}