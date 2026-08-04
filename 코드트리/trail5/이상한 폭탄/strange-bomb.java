import java.util.*;
import java.io.*;

/*
우선순위큐는 안됨 같은 종류 두번째 이상 폭탄인지 모르잖아

last[i] = i번 폭탄이 마지막으로 등장한 위치
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int K = read();
        HashMap<Integer, Integer> m = new HashMap<>();
        int ans = -1;
        for(int i = 0; i<N; i++){
            int cur = read();
            if(m.containsKey(cur)){
                int lastIdx = m.get(cur);
                if(i - lastIdx <= K)
                    ans = Math.max(ans, cur);
            }
            else
                m.put(cur, i);
        }
        System.out.print(ans);
    }
}