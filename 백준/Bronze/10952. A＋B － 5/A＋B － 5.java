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
        
        while(true){
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == 0 && b == 0)
                break;
            int ans = a+b;
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
