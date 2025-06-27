import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] parents = new int[N];
        Arrays.fill(parents, -1);

        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int maxIdx = 0;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<i; j++){
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parents[i] = j;
                }
            }
            if(dp[i] > dp[maxIdx])
                maxIdx = i;
        }

        List<Integer> lis = new ArrayList<>();
        int t = maxIdx;
        while(t != -1){
            lis.add(arr[t]);
            t = parents[t];
        }
        Collections.reverse(lis);

        StringBuilder sb = new StringBuilder();
        for(int i : lis){
            sb.append(i).append(" ");
        }

        bw.write(String.valueOf(lis.size()));
        bw.newLine();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
