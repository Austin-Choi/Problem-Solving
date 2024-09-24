import java.io.*;
// star shape
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        // N이 10만이라서
        Long min = (N-1L)+(N-2L)*(N-1L);
        StringBuilder sb = new StringBuilder(min+"\n");
        for(int i = 2; i<=N; i++){
            sb.append(1).append(" ").append(i).append("\n");
        }
        bw.write(sb+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}