import java.util.*;
import java.io.*;

public class Main {
    private static boolean isPalindrome(int num){
        char[] cl = String.valueOf(num).toCharArray();
        boolean palinFlag = true;
        for(int i = 0; i<cl.length/2; i++){
            if(cl[i] != cl[cl.length -1 - i]){
                palinFlag = false;
                break;
            }
        }
        return palinFlag;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            int num = Integer.parseInt(br.readLine());
            if(num == 0)
                break;
            if(isPalindrome(num))
                sb.append("yes");
            else
                sb.append("no");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
