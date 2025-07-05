import java.util.*;
import java.io.*;

public class Main {
    private static int F;
    private static int[] parent;
    private static int[] size;

    private static int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // union by size -> 트리 높이 최소화
    private static int union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            if (size[x] < size[y]) {
                parent[x] = y;
                size[y] += size[x];
                return size[y];
            } else {
                parent[y] = x;
                size[x] += size[y];
                return size[x];
            }
        }
        return size[x];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            F = Integer.parseInt(br.readLine());
            Map<String, Integer> m = new HashMap<>();
            int fidx = 1;
            parent = new int[200001];
            size = new int[200001];
            Arrays.fill(size, 1);
            for(int i = 0; i<parent.length; i++){
                parent[i] = i;
            }
            for(int f = 0; f<F; f++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if(!m.containsKey(a))
                    m.put(a, fidx++);

                if(!m.containsKey(b))
                    m.put(b, fidx++);

                int ai = m.get(a);
                int bi = m.get(b);

                bw.write(String.valueOf(union(ai, bi)));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}