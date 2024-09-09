
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
버스 비용 주어짐
이번엔 배열 말고 리스트로 풀어보자
-> 각 노드를 가지는 리스트를 정의하고 그 배열 요소마다 연결된 node 정보를 arrayList로 달아주기
-> ArrayList<ArrayList<Node>> nodeList;
weight이 모두 양수이기 때문에 우선순위 큐를 활용해서 최단 거리를 구하기 위해서는
다익스트라 알고리즘 쓸 거니까 시작점에서 각 정점으로 가는 최단거리를 갱신할 배열 정의
-> int[] dist;
다익스트라 알고리즘은 최단거리 갱신 배열을 최대값으로 설정하는 초기화 과정이 있음
-> 최대값 = 문제에서 주어진 간선의 최대 갯수 * 가중치의 최댓값
-> 여기서는 버스 갯수 ( 100,000 ) * 버스 비용 (100,000) = 100,000,000,000; (long 고려)
 */
class Node implements Comparable<Node>{
    int end; // 목적지
    int weight; // 비용

    Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node n){
        return weight - n.weight;
    }
}

public class Main {
    private static int[] dist;
    private static boolean[] check;
    //private static final long INF = 100000L * 100000L;
    private static final int INF = Integer.MAX_VALUE;

    private static int N, M;
    private static ArrayList<ArrayList<Node>> nodeList; // 인접리스트

    public static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;

            if(!check[cur]){
                check[cur] = true;

                for(Node n : nodeList.get(cur)){
                    if(!check[n.end] && dist[n.end] > dist[cur] + n.weight){
                        dist[n.end] = dist[cur] + n.weight;
                        pq.add(new Node(n.end, dist[n.end]));
                    }
                }
            }
        }
        return dist[end];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodeList = new ArrayList<>();
        dist = new int[N+1];
        check = new boolean[N+1];

        for(int i = 0; i<= N; i++){
            nodeList.add(new ArrayList<>());
        }

        // 한 도시에서 출발하여 다른 도시에 도착한다
        // 단방향 간선 정보 입력하기
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(start, end) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
