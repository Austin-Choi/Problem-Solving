import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Deque<Integer> st = new ArrayDeque<>();
        for(int i = 0; i<K; i++){
            int num = Integer.parseInt(br.readLine());
            if(num != 0)
                st.addLast(num);
            else
                st.pollLast();
        }

        int tot = 0;
        while(!st.isEmpty()){
            tot += st.pollFirst();
        }
        System.out.print(tot);
    }
}
