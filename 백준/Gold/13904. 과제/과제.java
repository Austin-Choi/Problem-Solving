import java.util.*;
import java.io.*;
/*
처음에 위상정렬느낌으로 d 작은 순으로 정렬하고 w큰순으로 정렬하는거 생각함
연속된 선택이 아니므로 부적합했고

d적은 순으로 정렬하고 pq 오름차순 기본 큐로 지정하고
pq 크기가 d보다 커지면 오름차순 pq니까 제일 작은거 앞에서 하나 poll 해주는 식으로
현재 처리일 수 ( pq 크기 ) 내에서 합이 최대화되는 집합 유지함
 */
public class Main {
    static int N;
    static int[][] w;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        w = new int[N][2];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w[i][0] = Integer.parseInt(st.nextToken());
            w[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(w, Comparator.comparingInt(a->a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i= 0; i<N; i++){
            pq.add(w[i][1]);
            if(pq.size() > w[i][0])
                pq.poll();
        }
        int ans = 0;
        while(!pq.isEmpty())
            ans += pq.poll();
        System.out.print(ans);
    }
}
