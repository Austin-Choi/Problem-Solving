
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
문자열 스택으로 ( ) [ ] . 일때만 push
현재 push 할게 ]면, peek 해서 [ 면 pop하고 계속 진행 아니면 push 하고 break;
현재 push 할게 )면, peek 해서 ( 면 pop하고 계속 진행 아니면 push
( [ . 은 push
마지막에 스택에 . 하나 들어있으면 yes, 아니면 no

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String cmd = br.readLine();
            // 입력 종료조건
            if(cmd.equals("."))
                break;

            boolean error = false;
            Stack<Character> st = new Stack<>();
            for(char s : cmd.toCharArray()){
                if(s == ']'){
                    if(!st.isEmpty() && st.peek() == '[') {
                        st.pop();
                    }
                    else{
                        error = true;
                        break;
                    }
                }
                else if(s == ')'){
                    if(!st.isEmpty() && st.peek() == '(') {
                        st.pop();
                    }
                    else{
                        error = true;
                        break;
                    }
                }
                else if(s == '(' || s == '[')
                    st.push(s);

            }

            if(st.isEmpty() && !error) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }

        }
        System.out.println(sb.toString());
    }
}
