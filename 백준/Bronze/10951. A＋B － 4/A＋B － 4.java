
import java.io.*;
import java.util.StringTokenizer;
/*
1 1
2 3
3 4
9 8
5 2
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean flag = true;
        while(flag){
            try{
                String s = br.readLine();
                StringTokenizer st = new StringTokenizer(s);
                int ans = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
                bw.write(ans+"\n");
            }
            catch (Exception e){
                flag = false;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}