import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        Map<String, Integer> m = new TreeMap<>();
        int M = N;
        while(M-->0){
            String k = br.readLine();
            m.put(k, m.getOrDefault(k, 0)+1);
        }

        StringBuilder sb = new StringBuilder();
        for(String k : m.keySet()){
            sb.append(k).append(" ").append(m.get(k)).append("\n");
        }
        System.out.print(sb);
    }
}