import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> stoiM = new HashMap<>();
        Map<Integer, String> itosM = new HashMap<>();
        for(int i = 1; i<=N; i++){
            String temp = br.readLine();
            stoiM.put(temp, i);
            itosM.put(i,temp);
        }
        for(int i = 0; i<M; i++){
            String temp = br.readLine();
            if(temp.matches("[0-9]+")){
                bw.write(itosM.get(Integer.parseInt(temp))+"\n");
            }
            else
                bw.write(stoiM.get(temp)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}