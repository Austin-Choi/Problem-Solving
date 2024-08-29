import java.io.*;
import java.util.*;

/*
pq 두개 나눠서 max랑 min힙을 두는데
왼쪽은 max 오른쪽은 min
근데 max를 항상 1개 많게 해주면 그게 항상 중간값이 됨.
그리고 항상 max의 peek보다 min의 peek값이 커야함.

->최소 힙과 최대 힙의 크기가 같으면 max에 저장하기(1개 많게하는거 유지하는 로직)
아니면 min에 저장하기
max.peek이 min.peek보다 크면 위치 바꿔서 삽입

답은 max를 peek 해주면 됨
 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            if(min.size() == max.size())
                max.add(num);
            else
                min.add(num);

            if(!max.isEmpty() && !min.isEmpty()) {
                if (max.peek() > min.peek()) {
                    int temp = min.poll();
                    int temp2 = max.poll();
                    max.add(temp);
                    min.add(temp2);
                }
            }
            sb.append(max.peek()+"\n");
        }
        System.out.print(sb);
    }
}
