
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            dq.add(i+1);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        int idx = 1;
        while(!dq.isEmpty()){
            if(idx != K){
                dq.addLast(dq.peekFirst());
                dq.pollFirst();
            }
            else{
                ans.add(dq.pollFirst());
                idx = 0;
            }
            idx++;
        }

        StringBuilder sb = new StringBuilder("<");
        for(int i = 0; i<ans.size(); i++){
            if(i != ans.size()-1){
                sb.append(ans.get(i)).append(", ");
            }
            else
                sb.append(ans.get(i));
        }
        sb.append(">");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
