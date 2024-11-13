import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0; i<5; i++){
            int a = Integer.parseInt(st.nextToken());
            sum += (a*a)%10;
        }
        int ans = sum % 10;
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
