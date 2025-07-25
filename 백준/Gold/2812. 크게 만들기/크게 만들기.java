/*
입력 시점에서 판단하기에는 그렇고 board로 쭉 다 받고
숫자가 나열되어있을때 어떤 숫자를 지웠을때 사전순으로 가장 늦게 등장하는 숫자가 되려면
앞에서부터 작은 숫자들을 제거하면 될 텐데
그냥 global하게 대소 기준으로 지워버리면 안될거 같고
작더라도 앞일수록 우선순위를 가지게 되나..?

4 1 7 7 2 5 2 8 4 1
- - 7 7 - 5 - 8 4 1
그렇다고 하기엔 위의 사례에서 맨끝 1이 살아남는게 말이 안됨

stack??
현재 숫자가 이전 숫자(stack에 들어있는것)보다 크면 pop하고 넣기
(K번)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int k = 0;
        Stack<Character> s = new Stack<>();
        int i = 0;
        String str = br.readLine();
        for(;i<N; i++){
            char cur = str.charAt(i);
            while(!s.isEmpty() && cur > s.peek() && k<K) {
                s.pop();
                k++;
            }
            s.add(cur);
        }

        // 만약 입력 내림차순이면 부족한 k개만큼 pop해야함
        // 4 3 2 1 -> 1 2 3 4이니까
        while(k<K){
            s.pop();
            k++;
        }

        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append(s.pop());
        }
        sb.reverse();

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
