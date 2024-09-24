import java.io.*;
import java.util.*;
// BFS적으로 각 노드마다 내려가면서
// 1부터 시작해서 K개만큼 채우고
// 1의 자식노드인 2,3,4 ... n-k 에 대해서 또 k만큼 채우고 반복해야함.

public class Main {
    private static int N;
    private static int K;
    private static int num = 2;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.add(1); // 1번노드부터 시작
        int curNode = 2; // 뒷번호

        int[] degree = new int[N+1]; // 1번 노드부터 사용, 각 노도의 자식 수 기록

        List<int[]> edges = new ArrayList<>();

        while(curNode <= N){
            int parent = q.poll();

            while (degree[parent] <K && curNode <= N){
                edges.add(new int[]{parent, curNode});
                q.add(curNode); // 자식 노드 큐에 추가
                degree[parent]++; // 부모 노드의 자식 수 증가
                degree[curNode]++; // 자식 노드도 1개의 간선이 연결됨
                curNode++;

            }
        }

        for(int[] edge : edges){
            bw.write(edge[0]+" "+ edge[1]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}