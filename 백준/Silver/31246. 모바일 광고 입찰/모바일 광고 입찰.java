import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> diffs = new ArrayList<>();
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int num = a-b;
            // diff가 0이상이면 바로 낙찰받을수 있는거
            if(num>=0)
                K--;
            else
                diffs.add(num);
        }
        Collections.sort(diffs, Collections.reverseOrder());
        // diff가 음수인것들
        // 차이가 덜 나는것들 먼저 배치하고
        int ans = 0;
        if(K > 0)
            ans = -(diffs.get(K-1));

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
