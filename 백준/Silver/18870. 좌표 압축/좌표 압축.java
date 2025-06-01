import java.util.*;
import java.io.*;

/*
각자 자기보다 작은 좌표 갯수 출력
입력배열 따로 저장해놓고
set에 넣고 빼서 pq로 정렬
몇번째로 출력되는지 map int int에 저장
그리고 입력배열 따로 저장한거 key값으로 읽으면서 value 출력
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        int[] inputs = new int[N];
        Set<Integer> s = new HashSet<>();
        Map<Integer, Integer> m = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int temp = Integer.parseInt(st.nextToken());
            inputs[i] = temp;
            s.add(temp);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(s);

        int pos = 0;
        while(!pq.isEmpty()){
            m.put(pq.poll(), pos++);
        }

        for(int i = 0; i<N; i++){
            sb.append(m.get(inputs[i]))
                    .append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
