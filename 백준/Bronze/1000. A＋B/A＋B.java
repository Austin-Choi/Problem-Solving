import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken());
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
