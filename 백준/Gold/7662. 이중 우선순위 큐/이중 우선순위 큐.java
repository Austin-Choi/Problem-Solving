
import java.util.*;
import java.io.*;

/*
SortedMap? Long, Integer -> 키, 갯수
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){

            SortedMap<Long, Integer> sm = new TreeMap<>();
            int K = Integer.parseInt(br.readLine());

            for(int k = 0; k<K; k++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                long num = Long.parseLong(st.nextToken());

                if(cmd.equals("I")){
                    sm.put(num, sm.getOrDefault(num, 0)+1);
                }
                else{
                    if(!sm.isEmpty()){
                        long target;
                        if(num == -1){
                            target = sm.firstKey();
                        }
                        else{
                            target = sm.lastKey();
                        }

                        if(sm.get(target)>0){
                            sm.put(target, sm.get(target)-1);
                        }

                        if(sm.get(target) == 0)
                            sm.remove(target);
                    }
                }
            }

            if(sm.isEmpty()){
                sb.append("EMPTY");
            }
            else{
                sb.append(sm.lastKey())
                        .append(" ")
                        .append(sm.firstKey());
            }
            sb.append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
