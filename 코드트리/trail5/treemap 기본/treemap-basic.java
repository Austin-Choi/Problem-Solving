import java.util.*;
import java.io.*;



public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void nl() throws IOException{
        st = new StringTokenizer(br.readLine());
    }

    static int ri(StringTokenizer st) throws IOException{
        return Integer.parseInt(st.nextToken());
    }
    static char[] rc(StringTokenizer st) throws IOException{
        return st.nextToken().toCharArray();
    }

    static int N;
    static Map<Integer, Integer> m = new TreeMap<>();

    public static void main(String[] args) throws IOException{
        nl();
        N = ri(st);
        StringBuilder sb = new StringBuilder();
        while(N-->0){
            nl();
            char cmd = rc(st)[0];
            
            if(cmd == 'p'){
                if(m.isEmpty()){
                    sb.append("None\n");
                }
                else{
                    for(int k : m.keySet())
                        sb.append(m.get(k)).append(" ");
                    sb.append("\n");
                }
            }
            else if(cmd == 'r'){
                int k = ri(st);
                m.remove(k);
            }
            else if(cmd == 'f'){
                int k = ri(st);
                if(m.containsKey(k))
                    sb.append(m.get(k));
                else
                    sb.append("None");
                sb.append("\n");
            }
            else{
                int k = ri(st);
                int v = ri(st);
                m.put(k, v);
            }
        }
        System.out.print(sb);
    }
}