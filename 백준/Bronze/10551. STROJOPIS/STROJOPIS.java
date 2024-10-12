import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> m = new HashMap<>();
        m.put("1", 0);
        m.put("Q", 0);
        m.put("A", 0);
        m.put("Z", 0);

        m.put("2", 1);
        m.put("W", 1);
        m.put("S", 1);
        m.put("X", 1);

        m.put("3", 2);
        m.put("E", 2);
        m.put("D", 2);
        m.put("C", 2);

        m.put("4", 3);
        m.put("R", 3);
        m.put("F", 3);
        m.put("V", 3);
        m.put("5", 3);
        m.put("T", 3);
        m.put("G", 3);
        m.put("B", 3);

        m.put("6", 4);
        m.put("Y", 4);
        m.put("H", 4);
        m.put("N", 4);
        m.put("7", 4);
        m.put("U", 4);
        m.put("J", 4);
        m.put("M", 4);

        m.put("8", 5);
        m.put("I", 5);
        m.put("K", 5);
        m.put(",", 5);

        m.put("9", 6);
        m.put("O", 6);
        m.put("L", 6);
        m.put(".", 6);

        m.put("0", 7);
        m.put("P", 7);
        m.put(";", 7);
        m.put("/", 7);
        m.put("-", 7);
        m.put("[", 7);
        m.put("'", 7);
        m.put("=", 7);
        m.put("]", 7);

        String[] input = br.readLine().split("");
        Map<Integer, Integer> count = new HashMap<>();
        for(String s : input){
            count.put(m.get(s), count.getOrDefault(m.get(s), 0)+1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<8; i++){
            if(count.get(i) == null){
                sb.append("0").append("\n");
                continue;
            }
            sb.append(count.get(i)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
