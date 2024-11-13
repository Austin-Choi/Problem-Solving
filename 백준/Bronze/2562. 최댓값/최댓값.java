import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        List<Integer> li = new ArrayList<>();
        while(flag){
            try{
                li.add(Integer.parseInt(br.readLine()));
            }
            catch (Exception e){
                flag = false;
            }
        }
        int maxIdx = 0;
        int max = 0;
        for(int idx = 0; idx < li.size(); idx++){
            if(li.get(idx)>max){
                max = li.get(idx);
                maxIdx = idx;
            }
        }
        maxIdx++;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(max+"\n");
        bw.write(maxIdx+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
