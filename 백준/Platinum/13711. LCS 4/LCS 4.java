
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] b = new int[N];
        int[] pos = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<N; i++){
            m.put(Integer.parseInt(st.nextToken()), i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            b[i] = m.get(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> lis = new ArrayList<>();

        for(int x : b){
            int idx = Collections.binarySearch(lis, x);

            if(idx < 0)
                idx = -(idx + 1);

            if(idx == lis.size())
                lis.add(x);
            else
                lis.set(idx, x);
        }

        bw.write(String.valueOf(lis.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}