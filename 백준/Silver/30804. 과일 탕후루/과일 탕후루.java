
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] il = new int[N];
        for(int n = 0; n<N; n++){
            il[n] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int maxFruit = 0;
        Map<Integer, Integer> m = new HashMap<>();

        for(int end = 0; end<N; end++){
            m.put(il[end], m.getOrDefault(il[end], 0)+1);

            while(m.size() > 2){
                m.put(il[start], m.get(il[start]) - 1);
                if(m.get(il[start]) == 0){
                    m.remove(il[start]);
                }
                start++;
            }

            maxFruit = Math.max(maxFruit, end-start+1);
        }

        bw.write(String.valueOf(maxFruit));
        bw.flush();
        bw.close();
        br.close();
    }
}
