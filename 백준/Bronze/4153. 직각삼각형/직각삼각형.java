
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> li = new ArrayList<>();
            while(st.hasMoreTokens()){
                li.add(Integer.parseInt(st.nextToken()));
            }
            if(li.get(0) == 0)
                break;
            Collections.sort(li);
            int a = (int) Math.pow(li.get(0), 2);
            int b = (int) Math.pow(li.get(1), 2);
            int c = (int) Math.pow(li.get(2), 2);
            if(a+b == c)
                sb.append("right");
            else
                sb.append("wrong");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
