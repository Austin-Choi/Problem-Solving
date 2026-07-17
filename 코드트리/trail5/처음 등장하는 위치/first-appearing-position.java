import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        Map<Integer, Integer> m = new TreeMap<>();
        for(int i = 1; i<=N; i++){
            int cur = read();
            if(!m.containsKey(cur)){
                m.put(cur, i);
            }
        }
        StringBuilder sb =new StringBuilder();
        for(int k : m.keySet()){
            sb.append(k).append(" ").append(m.get(k)).append("\n");
        }
        System.out.print(sb);
    }
}