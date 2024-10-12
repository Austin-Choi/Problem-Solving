import java.io.*;

/*
BBBA
BBABA
BABABA
ABABABA

4
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int m = 1;
        for(int i = N-1; i>=0; i--, m++){
            for(int j = 0; j<i; j++){
                sb.append(" ");
            }
            for(int k = 0; k<m*2; k++){
                if(k%2 == 0)
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
