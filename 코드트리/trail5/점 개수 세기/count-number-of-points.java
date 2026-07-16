import java.util.*;
import java.io.*;

// treeset celing, floor 활용

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // 좌표값 정렬
    static TreeSet<Integer> sx;
    // 실제 좌표값, 인덱스 
    static HashMap<Integer, Integer> m;

    // p 이상을 만족하는 최솟값
    static int lb(int p, ArrayList<Integer> li){
        Integer x = sx.ceiling(p);
        if(x == null)
            return li.size()+1;
        return m.get(x);
    }

    // p 이하를 만족하는 최댓값
    static int ub(int p, ArrayList<Integer> li){
        Integer x = sx.floor(p);
        if(x == null)
            return 0;
        return m.get(x);
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int Q = read();

        sx = new TreeSet<>();
        for(int i = 0; i<N; i++){
            int cur = read();
            sx.add(cur);
        }
        m = new HashMap<>();
        int idx = 1;
        for(int xx : sx){
            m.put(xx, idx++);
        }
        ArrayList<Integer> li = new ArrayList<>(sx);

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();

            int lb = lb(a, li);
            int ub = ub(b, li);

            if(lb > ub)
                sb.append(0);
            else
                sb.append(ub - lb+1);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}