import java.util.*;
import java.io.*;

/*
x오름차순, y오름차순 정렬

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int D = read();
        int[][] pos = new int[N][2];
        for(int i = 0; i<N; i++){
            pos[i] = new int[]{read(), read()};
        }
        Arrays.sort(pos, (a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        int ans = 1_000_001;
        int l = 0;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        
        for(int r = 0; r<N; r++){
            m.put(pos[r][1], m.getOrDefault(pos[r][1], 0)+1);
            // 조건 만족하는 동안 당기기
            // key가 y값이니까
            while(!m.isEmpty() && m.lastKey() - m.firstKey() >= D){
                ans = Math.min(ans, pos[r][0] - pos[l][0]);

                int y = pos[l][1];
                int cnt = m.get(y);

                if(cnt == 1)
                    m.remove(y);
                else
                    m.put(y, cnt-1);
                l++;
            }
        }

        System.out.print(ans == 1_000_001 ? -1 : ans);
    }
}