/*
26
1*26+ 11

주어진 문자를 문제 조건대로 나열하는 것은 
암호로 쓸 수 있는 문자의 길이를 n이라 하면 n진법이 되고
아래는 map으로 해싱해서 진법을 10진법으로 표현하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static char[] input;
    static char[] target;
    static final int MOD = 900528;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        Map<Character, Integer> m = new HashMap<>();
        for(int i =0; i<input.length; i++){
            m.put(input[i], i+1);
        }
        target = br.readLine().toCharArray();
        int[] pow = new int[target.length];
        pow[0] = 1;
        for(int i = 1; i< target.length; i++){
            pow[i] = (pow[i-1] * input.length) % MOD;
        }
        int ans = 0;
        for(int i = 0; i<target.length; i++){
            ans = (ans + (m.get(target[i]) * pow[target.length-i-1]) % MOD)%MOD;
        }
        System.out.print(ans);
    }
}
