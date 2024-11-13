import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // a 97
    // 각각의 알파벳에 대해서 처음 등장하는 위치
    // 단어에 포함되어있지 않다면 -1출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        int[] ans = new int[26];
        Arrays.fill(ans, -1);
        Map<Character, Integer> m = new HashMap<>();
        for(int i = 0; i<temp.length; i++){
            int cNum = (int) temp[i] - 97;
            m.put(temp[i], m.getOrDefault(temp[i], 0)+1);
            if(m.get(temp[i]) == 1)
                ans[cNum] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i : ans){
            sb.append(i).append(" ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
