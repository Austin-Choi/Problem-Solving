/*
무한 루프에 빠졌는지 알려면
일단 5천만번 실행함
-> 끝났으면 무한루프 아님
-> 안끝남 -> 무한 루프 위치 찾아야함

한 루프당 최대 5천만번 수행이니까 5천만번 또 돌리면 무한 루프가 뭐든 간에
그 무한 루프가 마지막까지 돌아가고 있을거임
그러면 counter는 그 무한 루프의 마지막 까지만 도달하고 그 안에서 뺑뺑이돌거라
5천만번 이후 최대치 counter를 따로 저장해서 그 counter를 포함하는 loop 찾기?

-> 명령어가 어떤 [와 가장 가까운지 해당 [인덱스 저장하는
inside[] 베열 pair 만들때 계산해서 O(1)
 */
import java.util.*;
import java.io.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] mem;
    static int ptr;
    static char[] code;
    // 대괄호 서로의 쌍 저장함
    static int[] pair;
    static int[] inside;
    static String input;
    static int inptr;
    static final int MOD = 256;
    static int sm,sc,si;
    static final long LIM1 = 50_000_000L;
    static final long LIM2 = 100_000_000L;
    static void interpreter(char op){
        switch (op){
            case '+': mem[ptr] = (mem[ptr] + 1) % MOD; break;
            case '-': mem[ptr] = (mem[ptr] + MOD-1) % MOD; break;
            case '>': ptr = (ptr + 1) % sm; break;
            case '<': ptr = (ptr + sm-1) % sm; break;
            case ',':
                if(inptr < si)
                    mem[ptr] = input.charAt(inptr++);
                else
                    mem[ptr] = MOD-1;
                break;
        }
    }

    static void mappingPair(){
        pair = new int[sc];
        inside = new int[sc];

        Deque<Integer> st = new ArrayDeque<>();
        for(int i = 0; i<sc; i++){
            if(st.isEmpty())
                inside[i] = -1;
            else
                inside[i] = st.peek();

            if(code[i]=='[')
                st.push(i);
            else if(code[i]==']'){
                int other = st.pop();
                pair[other] = i;
                pair[i] = other;
            }
        }
    }

    static void run(){
        mappingPair();

        int counter = 0;
        long step = 0;
        int maxCounter = -1;

        while(counter < sc && step < LIM2){
            char op = code[counter];

            if(op=='['){
                if(mem[ptr] == 0)
                    counter = pair[counter]+1;
                else
                    counter++;
            }
            else if(op==']'){
                if(mem[ptr] != 0)
                    counter = pair[counter]+1;
                else
                    counter++;
            }
            else{
                interpreter(op);
                counter++;
            }

            step++;
            if(step > LIM1 && counter < sc)
                maxCounter = Math.max(maxCounter, counter);
        }

        if(counter >= sc){
            sb.append("Terminates\n");
            return;
        }

        //counter가 sc 안에 있다면 안끝난거임
        int i,j;
        if(code[maxCounter]=='['){
            i = maxCounter;
            j = pair[i];
        }
        else if(code[maxCounter]==']'){
            j = maxCounter;
            i = pair[j];
        }
        else{
            i = inside[counter];
            j = pair[i];
        }

        sb.append("Loops ").append(i).append(" ").append(j).append("\n");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken());

            mem = new int[sm];
            code = br.readLine().toCharArray();
            pair = new int[sc];
            input = br.readLine();

            ptr = 0;
            inptr = 0;

            run();
        }
        System.out.println(sb);
    }
}
