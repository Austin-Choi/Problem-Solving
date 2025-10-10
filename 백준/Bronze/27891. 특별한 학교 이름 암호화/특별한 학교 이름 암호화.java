import java.util.*;
import java.io.*;
public class Main {
    static String[] arr = {
        "North London Collegiate School",
        "Branksome Hall Asia",
        "Korea International School",
        "St. Johnsbury Academy"
    };
    static String[] arr2 = {
            "NLCS", "BHA", "KIS", "SJA"
    };
    static Map<String, String> m = new HashMap<>();
    static String solve(String input){
        String[] actual = Arrays.copyOfRange(arr,0,arr.length);
        for(int i = 0; i<4; i++){
            String s = arr[i];
            s = s.replaceAll("[\\p{Punct}\\s]+", "");
            s = s.substring(0,10);
            char[] temp = s.toCharArray();
            for(int idx = 0; idx < temp.length; idx++){
                if(Character.isUpperCase(temp[idx])){
                    temp[idx] = Character.toLowerCase(temp[idx]);
                }
            }
            char[] il = input.toCharArray();
            for(int j = 0; j<26; j++){
                boolean can = true;
                for(int k = 0; k<10; k++){
                    char c = (char) ((temp[k] - 'a' + j)%26 + 'a');
                    if(il[k] != c){
                        can = false;
                        break;
                    }
                }
                if(can)
                    return actual[i];
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<4; i++){
            m.put(arr[i], arr2[i]);
        }
        System.out.println(m.get(solve(br.readLine())));
    }
}
