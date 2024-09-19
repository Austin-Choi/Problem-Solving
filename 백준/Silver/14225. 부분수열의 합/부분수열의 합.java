import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] pool = new int[N];
        for(int nn = 0; nn < N; nn++){
            pool[nn] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pool);
        int num = 0;
        for(int i : pool){
            if(num + 1 < i){
                break;
            }
            else
                num += i;
        }
        num++;
        bw.write(num+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
