import java.io.*;
public class Main {
    static int T, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            N = Integer.parseInt(br.readLine());

            char[] tmp = br.readLine().toCharArray();
            int total = 0;
            for(int i = 0; i<N; i++){
                total += tmp[i]-'0';
            }
            br.readLine();

            int ans = (total + 2) / 3;
            bw.write(String.valueOf(ans));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
