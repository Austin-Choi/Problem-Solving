/*
제2공개키랑 암호문이랑 위치 1대1로 해서 매칭시키고
제1공개키 키로 값(암호문) 불러와서 읽기?
 */
import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<String> first;
    static Map<String, String> sec;

    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            first = new ArrayList<>();
            sec = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                first.add(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                sec.put(st.nextToken(), st2.nextToken());
            }
            for(String key : first){
                sb.append(sec.get(key)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
