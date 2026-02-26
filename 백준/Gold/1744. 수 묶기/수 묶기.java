/*
정렬하고 큰거랑 그다음 큰거 비교함
묶은 결과가 큰거보다 크면 묶기, 그다음 큰거랑 큰거 사이 (즉 그다음 큰거보다 크면) 묶기
아니면 큰거만 더해주기
-> 음 근데 음수랑 양수 따로 해야하지 않을까

1은 다 더해주고 0은 음수 처리하는데 쓰고
음수는 절대값 기준으로 큰거부터 우선순위
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        int ans = 0;
        int zeros = 0;
        while(N-->0){
            int a = Integer.parseInt(br.readLine());
            if(a == 1)
                ans += a;
            else if(a == 0)
                zeros++;
            else if(a < 0)
                pq2.add(a);
            else
                pq.add(a);
        }


        while(pq.size()>1){
            int a = pq.poll();
            int b = pq.poll();
            if(a*b > a || a*b > b){
                ans += a*b;
            }
            else{
                ans += a;
                pq.add(b);
            }
        }

        if(!pq.isEmpty())
            ans += pq.poll();

        while(pq2.size()>1){
            int a = pq2.poll();
            int b = pq2.poll();
            ans += a*b;
        }

        if(!pq2.isEmpty()){
            int a = pq2.poll();
            if(zeros > 0)
                zeros--;
            else
                ans += a;
        }

        System.out.print(ans);
    }
}
