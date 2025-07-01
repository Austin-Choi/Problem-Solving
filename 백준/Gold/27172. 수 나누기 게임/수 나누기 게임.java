import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> m = new LinkedHashMap<>();
        for(int i =0; i<N; i++){
            m.put(Integer.parseInt(st.nextToken()), 0);
        }

        int limit = 0;
        OptionalInt max = m.keySet().stream().mapToInt(x->x).max();
        if(max.isPresent()){
            limit = max.getAsInt();
        }

        for(int k : m.keySet()){
            int i = 2;
            while(k*i <= limit){
                if(m.get(k*i) != null){
                    m.put(k, m.get(k)+1);
                    m.put(k*i, m.get(k*i)-1);
                }
                i++;
            }
        }

        for(int k : m.keySet()){
            sb.append(m.get(k)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
