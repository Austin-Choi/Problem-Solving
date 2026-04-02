import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static HashMap<String, Integer> m = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while(N-->0){
            String s = br.readLine();
            m.put(s, m.getOrDefault(s, 0)+1);
        }
        int ans = 0;
        while(M-->0){
            String toFind = br.readLine();
            if(m.keySet().contains(toFind)){
                ans += m.get(toFind);
            }
        }
        System.out.println(ans);
    }
}
