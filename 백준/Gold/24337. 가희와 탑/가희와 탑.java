import java.util.*;
import java.io.*;
/*
건물 갯수 N,
왼->오 가장 긴 증가하는 수열의 길이 a
-> 1부터 순서대로 증가하기
오->왼 가장 긴 증가하는 수열의 길이 b
-> a에서 있던 가장 큰값에서 1씩 내려오기
-> N-a-b 만큼 1로 채우기
-> 사전순으로 가장 앞선 것 : 6 4 3이라고 하면 1 2 3 4 3 2보다
1 2 3 4 2 1이 더빠름
 */
public class Main {
    static int N, a, b;
    static Deque<Integer> rst = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if(a+b-1 > N){
            System.out.print(-1);
            return;
        }

        if(a == 1){
            //a가 1이면 제일 큰거 앞에
            rst.addLast(b);
            for(int i = 0; i<N-b; i++)
                rst.addLast(1);
            for(int i = b-1; i>=1; i--)
                rst.addLast(i);
        }else if(b == 1){
            for(int i = 0;  i<N-a; i++)
                rst.addLast(1);
            for(int i= 1; i<=a; i++)
                rst.addLast(i);
        }
        else {
            // a
            for(int i = 1; i<a; i++)
                rst.add(i);
            rst.addLast(Math.max(a,b));
            // b
            for(int n=b-1; n>=1; n--)
                rst.addLast(n);
            // 앞에 1 붙이기
            while(rst.size() < N)
                rst.addFirst(1);
        }

        StringBuilder sb = new StringBuilder();
        for(int n : rst)
            sb.append(n).append(" ");
        System.out.print(sb);
    }
}
