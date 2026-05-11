import java.util.*;
import java.io.*;

/*
counting DP 기법으로 O(N)으로 최적화
부분수열이니까 dp배열 생략하고 경우의 수 누적해주면 됨
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String in = br.readLine();
        char[] t = in.toCharArray();
        
        int ans = 0;
        // for(int i = 0; i<N-2; i++){
        //     if(t[i] != 'C')
        //         continue;
        //     for(int j = i+1; j<N-1; j++){
        //         if(t[j] != 'O')
        //             continue;
        //         for(int k = j+1; k<N; k++){
        //             if(t[k] == 'W')
        //                 ans++;
        //         }
        //     }
        // }
        int cc = 0;
        int co = 0;
        int cw = 0;
        for(int i = 0; i<N; i++){
            if(t[i] =='C')
                cc++;
            if(t[i] == 'O')
                co += cc;
            if(t[i] == 'W')
                ans += co;
        }
        System.out.print(ans);
    }
}
