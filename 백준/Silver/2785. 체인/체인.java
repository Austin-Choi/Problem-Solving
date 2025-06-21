
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> al = new ArrayList<>();

        for(int i = 0; i<N; i++){
            al.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(al);
        Deque<Integer> dq = new ArrayDeque<>(al);

        int ans = 0;
        while(true){
            if(dq.size() == 1)
                break;

            if(dq.peekFirst() == 0)
                dq.pollFirst();
            else{
                int min = dq.pollFirst();
                dq.addFirst(min - 1);
                int max = dq.pollLast();
                int sndMax = dq.pollLast();
                dq.addLast(max+sndMax+1);
                ans++;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
