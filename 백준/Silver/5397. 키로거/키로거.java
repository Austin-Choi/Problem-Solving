import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-->0){
            char[] cmds = br.readLine().toCharArray();
            Stack<Character> answer = new Stack<>();
            Stack<Character> input = new Stack<>();

            for(char c : cmds){
                if(c == '<'){
                    if(!answer.isEmpty()){
                        input.push(answer.pop());
                    }
                }
                else if(c == '>'){
                    if(!input.isEmpty()){
                        answer.push(input.pop());
                    }
                }
                // 백스페이스
                else if(c == '-'){
                    if(!answer.isEmpty())
                        answer.pop();
                }
                else{
                    // 문자 붙여주기
                    answer.push(c);
                }
            }
            while(!input.isEmpty()){
                answer.push(input.pop());
            }

            StringBuilder sb2 = new StringBuilder();
            while(!answer.isEmpty()){
                sb2.append(answer.pop());
            }
            sb.append(sb2.reverse()+"\n");
        }
        System.out.print(sb);
    }
}