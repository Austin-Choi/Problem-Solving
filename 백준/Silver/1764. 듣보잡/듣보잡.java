import java.util.*;
import java.io.*;

/*
처음에 map에 넣고
두번째에 넣을때 exist면 sb에 append
 */

public class Main {
    private static int N;
    private static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, Integer> m = new HashMap<>();
        for(int i = 0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            m.put(name, m.getOrDefault(name, 0)+1);
        }

        ArrayList<String> ans = new ArrayList<>();
        for(Map.Entry<String, Integer> e : m.entrySet()){
            if(e.getValue()>1){
                ans.add(e.getKey());
            }
        }

        Collections.sort(ans);

        sb.append(ans.size());
        sb.append("\n");
        for (String an : ans) {
            sb.append(an);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
