/*
반례 96 -> 4 4 6
반례 120 -> 4 30
반례 22 -> -1, 74 -> -1
 */
import java.util.*;
import java.io.*;
public class Main {
    // N의 소인수리스트
    static ArrayList<Long> li = new ArrayList<>();
    static void factorize(long n){
        while(n % 2 == 0){
            li.add(2L);
            n /= 2;
        }

        for(long i = 3; i*i<= n; i+=2){
            while(n%i == 0){
                li.add(i);
                n /= i;
            }
        }
        if(n > 1)
            li.add(n);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long N = Long.parseLong(br.readLine());
        factorize(N);
        // N이 소수일때만 안됨
        if(li.size()==1) {
            System.out.println(-1);
            return;
        }

        int l = li.size();
        //-> 한쌍씩 묶어주고 맨 마지막에 1개 남으면 걔까지 묶어주면 사전순 오름차순됨
        for(int i = 1; i<l; i+=2){
            long cur = li.get(i)*li.get(i-1);
            if(i == l-2)
                cur *= li.get(i+1);
            sb.append(cur).append(" ");
        }
        System.out.println(sb);
    }
}
