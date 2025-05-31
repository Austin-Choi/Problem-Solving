import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Map<String, String> m = new HashMap<>();
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            m.put(st.nextToken(), st.nextToken());
        }

        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            String pw = m.get(st.nextToken());
            sb.append(pw);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}