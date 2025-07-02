import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] makePrimeList(){
        ArrayList<Integer> pl = new ArrayList<>();
        int[] nl = new int[N+1];
        for(int i = 2; i<=N; i++){
            int j = 2;
            for(;i*j<=N; j++){
                if(nl[i*j] == 0)
                    nl[i*j] = -1;
            }
        }

        for(int i = 2; i<nl.length; i++){
            if(nl[i] == 0)
                pl.add(i);
        }
        int[] rst = new int[pl.size()];
        for(int i = 0; i<pl.size(); i++){
            rst[i] = pl.get(i);
        }
        return rst;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] pSums = makePrimeList();
        int ans = 0;
        int l = 0;
        int r = 0;
        int temp = 0;

        while(true){
            if(temp >= N)
                temp -= pSums[l++];
            else if(r == pSums.length)
                break;
            else
                temp += pSums[r++];

            if(temp == N)
                ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
