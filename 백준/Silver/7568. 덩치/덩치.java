import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] ppl = new int[N][2];
        
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ppl[i][0] = Integer.parseInt(st.nextToken());
            ppl[i][1] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            int r = 1;
            for(int j = 0; j<N; j++){
                if(ppl[j][0] > ppl[i][0]
                    && ppl[j][1] > ppl[i][1])
                    r++;
            }
            sb.append(r).append(" ");
        }
        System.out.print(sb);
    }
}