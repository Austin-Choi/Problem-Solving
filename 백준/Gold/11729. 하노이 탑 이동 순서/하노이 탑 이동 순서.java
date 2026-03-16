/*
1 3

1 2
1 3
2 3

1 3
1 2
3 2
1 3
2 1
2 3
1 3

n-1개를 1-2
가장 큰거 1-3
n-1개 2-3
 */
import java.io.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static void hanoi(int n, int from, int mid, int to){
        if(n == 1){
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }

        // n-1개 2번으로 치움
        hanoi(n-1, from,to,mid);
        // 제일 큰거 (n번) 3번에 깔기
        sb.append(from).append(" ").append(to).append("\n");
        // n-1개 3으로 치움
        hanoi(n-1, mid, from, to);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println((1<<n)-1);
        hanoi(n, 1,2,3);
        System.out.print(sb);
    }
}