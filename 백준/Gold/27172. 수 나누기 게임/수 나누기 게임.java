import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        boolean[] exists = new boolean[1_000_001];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            exists[arr[i]] = true;
            max = Math.max(max, arr[i]);
        }

        int[] scores = new int[1_000_001];

        for(int i = 0; i < N; i++){
            int k = arr[i];
            for(int j = k * 2; j <= max; j += k){
                if(exists[j]){
                    scores[k]++;
                    scores[j]--;
                }
            }
        }

        for(int i = 0; i < N; i++){
            sb.append(scores[arr[i]]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}