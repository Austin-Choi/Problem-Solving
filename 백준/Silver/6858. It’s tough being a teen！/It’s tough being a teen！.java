import java.io.*;
import java.util.*;

public class Main {
    // 연결정보
    private static List<Integer>[] arr;
    // 진입차수를 저장하는 배열
    // 인덱스 값은 노드 번호
    private static int[] count;
    private static List<Integer> result;
    private static boolean topologySort(){
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 1; i<8; i++){
            // 초기에 진입차수가 0이 된 정점을 큐에 삽입
            if(count[i] == 0) {
                q.add(i);
            }
        }
        for(int i = 1; i<8; i++){
            if(!q.isEmpty()){
                // 큐에서 정점을 하나 꺼내서
                int idx = q.poll();
                // 결과에 저장
                result.add(idx);
                // 연결되어있는 정점들과의 간선을 다 끊어주고
                // 즉, count[연결된정점]에서 1씩 빼주기
                for(int ii : arr[idx]){
                    count[ii] -= 1;
                    // 새롭게 진입차수가 0이 된 정점을 큐에 삽입
                    if(count[ii] == 0)
                        q.add(ii);
                }
            }
        }
        // 사이클 발생하면 result의 크기는 7이 아님.
        return result.size() == 7;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new ArrayList[8];
        for(int i= 0; i<8; i++){
            arr[i] = new ArrayList<>();
        }
        count = new int[8];
        result = new ArrayList<>();

        arr[1].add(7);
        count[7]++;
        arr[1].add(4);
        count[4]++;
        arr[2].add(1);
        count[1]++;
        arr[3].add(4);
        count[4]++;
        arr[3].add(5);
        count[5]++;

        while(true){
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            if(a==0 && b==0)
                break;
            arr[a].add(b);
            count[b]++;
        }

        if(topologySort()) {
            for(int n : result)
                bw.write(n+" ");
        }
        else{
            bw.write("Cannot complete these tasks. Going to bed.");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
