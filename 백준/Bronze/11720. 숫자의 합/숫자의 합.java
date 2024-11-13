
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br.readLine();
        long sum = 0;

        String[] temp = br.readLine().split("");
        for(String s : temp){
            sum += Long.parseLong(s);
        }
        bw.write(sum+"");
        bw.flush();
        bw.close();
        br.close();
    }
}