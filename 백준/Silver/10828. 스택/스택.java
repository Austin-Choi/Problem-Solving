
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> s = new ArrayDeque<>();

        for(int n = 0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("push")){
                int num = Integer.parseInt(st.nextToken());
                s.addFirst(num);
            }
            else if(cmd.equals("top")){
                if(!s.isEmpty())
                    sb.append(s.peek()).append("\n");
                else
                    sb.append(-1+"\n");
            }
            else if(cmd.equals("size")){
                sb.append(s.size()).append("\n");
            }
            else if(cmd.equals("empty")){
                if(s.isEmpty())
                    sb.append(1+"\n");
                else
                    sb.append(0+"\n");
            }
            else{
                if(!s.isEmpty())
                    sb.append(s.poll()).append("\n");
                else
                    sb.append(-1+"\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
