import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());
        long tot = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            tot += Long.parseLong(st.nextToken());
        if(tot % X == 0)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
