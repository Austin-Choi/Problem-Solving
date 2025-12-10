/*
문자열 읽어와서 숫자부분 long으로 변환하고
숫자랑 연산자부분 구분해서 뒤에서부터 받아서
숫자 리스트, 연산자 리스트 뒤집어서 만듬

일단 숫자 숫자스택에 먼저 넣음

현재 연산자보다 top이 더 우선순위가 높은 동안
숫자 스택에서 두개 피연산자 꺼내서 적용하고 숫자 스택에 넣음
현재 연산자는 연산자 스택에 넣음
 */
import java.util.*;
import java.io.*;
public class Main {
    static class Token{
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
    }
    static Token tokenize(String s){
        Token t = new Token();
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                sb.append(c);
            }
            else{
                if(sb.length()>0){
                    String num = sb.reverse().toString();
                    long val = Long.parseLong(num);
                    t.nums.add(val);
                    sb.setLength(0);
                }
                t.ops.add(c);
            }
        }
        if(sb.length()>0){
            String num = sb.reverse().toString();
            long val = Long.parseLong(num);
            t.nums.add(val);
        }

        return t;
    }
    static long calc(long a, long b, char op){
        if(op == '+')
            return a+b;
        if(op == '-')
            return a-b;
        if(op == '*')
            return a*b;
        return a/b;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Character, Integer> priorityMap = new HashMap<>();

        priorityMap.put('+', Integer.parseInt(st.nextToken()));
        priorityMap.put('-', Integer.parseInt(st.nextToken()));
        priorityMap.put('*', Integer.parseInt(st.nextToken()));
        priorityMap.put('/', Integer.parseInt(st.nextToken()));

        Token t = tokenize(br.readLine());

        Deque<Long> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        int N = t.nums.size();
        int O = t.ops.size();

        for(int i = 0; i<N; i++){
            numStack.push(t.nums.get(i));
            if(i < O){
                char op = t.ops.get(i);
                // 숫자 크면 우선순위높은거
                // 현재꺼보다 우선순위 높거나 같은거 죄다 처리하기
                while(!opStack.isEmpty() && !(priorityMap.get(opStack.peek()) < priorityMap.get(op))){
                    char top = opStack.pop();
                    long b = numStack.pop();
                    long a = numStack.pop();
                    numStack.push(calc(a,b,top));
                }
                opStack.push(op);
            }
        }

        while(!opStack.isEmpty()){
            char top = opStack.pop();
            // 오타;
            long b = numStack.pop();
            long a = numStack.pop();
            numStack.push(calc(a,b,top));
        }
        System.out.println(numStack.pop());
    }
}
