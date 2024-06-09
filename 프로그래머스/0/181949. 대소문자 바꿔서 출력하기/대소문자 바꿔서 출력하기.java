import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        
        char[] lc = s.toCharArray();
        
        for(int i = 0; i<lc.length; i++){
            if(Character.isLowerCase(lc[i]))
                lc[i] = Character.toUpperCase(lc[i]);
            else if(Character.isUpperCase(lc[i]))
                lc[i] = Character.toLowerCase(lc[i]);
        }
        for(char c : lc){
            sb.append(c);
        }
        System.out.print(sb.toString());
    }
}