import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] temp = br.readLine().toCharArray();
        bw.write((int) temp[0]+"");
        bw.flush();
        bw.close();
        br.close();
    }
}