
import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
    int a;
    int b;

    public Info(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Info o){
        return this.a - o.a;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // parent 배열 구성을 위해 최대 정점 숫자를 알아야함
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int maxS = 0;
        int maxE = 0;
        for(int n = 0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            maxS = Math.max(s, maxS);
            maxE = Math.max(e, maxE);
            pq.add(new Info(s,e));
        }

        int size = Math.max(maxS, maxE);
        int[] board = new int[N];
        int[] parent = new int[size+1];

        for(int i = 0; i<N; i++){
            Info cur = pq.poll();
            board[i] = cur.b;
            parent[cur.b] = cur.a;
        }

        // 이분탐색을 이용해 lis를 구하기
        // li.add와 set에서 실제 board에서의 인덱스를 저장하는
        // record 배열을 놓고 i<N 일때 record[i] 에 binarysearch 결과 idx 저장
        // board를 읽어오고
        // 거기에 해당하지 않는 board안의 숫자를 parent 배열을 이용해
        // 읽어오기
        ArrayList<Integer> li = new ArrayList<>();
        // board[i] 가 LIS의 몇번째 위치인지 기록
        int[] record = new int[N];
        for(int i = 0; i<N; i++){
            int idx = Collections.binarySearch(li, board[i]);
            if(idx < 0)
                idx = -(idx+1);

            if(idx == li.size())
                li.add(board[i]);
            else
                li.set(idx, board[i]);

            record[i] = idx;
        }

        int maxLimit = li.size();
        int ansSize = N - maxLimit;
        bw.write(String.valueOf(ansSize));
        bw.newLine();

        maxLimit--;
        Set<Integer> lisS = new HashSet<>();
        for(int j = N-1; j>=0; j--){
            if(record[j] == maxLimit){
                lisS.add(board[j]);
                maxLimit--;
            }
        }

        List<Integer> toRemove = new ArrayList<>();
        for(int j = 0; j < N; j++){
            if(!lisS.contains(board[j])){
                toRemove.add(parent[board[j]]);
            }
        }
        Collections.sort(toRemove);
        for(int n : toRemove){
            bw.write(String.valueOf(n));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
