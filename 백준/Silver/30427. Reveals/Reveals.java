import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static Map<String, Integer> house;
    static Set<String> out;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        house = new HashMap<>();
        out = new HashSet<>();
        br.readLine();
        N = Integer.parseInt(br.readLine());
        for(int i = 0;i <N; i++){
            String k = br.readLine();
            house.put(k, house.getOrDefault(k, 0)+1);
        }
        M = Integer.parseInt(br.readLine());
        for(int i =0 ;i<M; i++){
            out.add(br.readLine());
        }

        if(house.containsKey("dongho")){
            System.out.print("dongho");
            return;
        }

        for(String s : out){
            house.remove(s);
        }
        Set<String> ss = house.keySet();
        Iterator<String> it = ss.iterator();
        if(ss.size() == 1){
            System.out.print(it.next());
            return;
        }
        if(ss.isEmpty()){
            System.out.print("swi");
            return;
        }
        if(ss.contains("bumin")){
            System.out.print("bumin");
            return;
        }
        if(ss.contains("cake")){
            System.out.print("cake");
            return;
        }
        if(ss.contains("lawyer")){
            System.out.print("lawyer");
            return;
        }
        String ans = null;
        for(String s : ss){
            if(ans == null || s.compareTo(ans) < 0)
                ans = s;
        }
        System.out.print(ans);
    }
}
