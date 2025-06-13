import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static boolean isPrime(int n){
        if(n == 1)
            return false;

        for(int i = 2; i<=Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> li = new ArrayList<>();
        while(st.hasMoreTokens()){
            li.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        for(int n : li){
            if(isPrime(n))
                ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
