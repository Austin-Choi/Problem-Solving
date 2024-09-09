import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int end;
    int weight;

    public Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node n){
        return Integer.compare(this.weight, n.weight);
    }
}

public class Main {
    private static int N;
    private static int M;
    private static boolean[] check;
    private static int[] dist;

    private static ArrayList<ArrayList<Node>> nodeList;

    private static int[] parent;

    public static int dijkstra(int start, int end){
        // 간선갯수 100,000 , 비용 100,000
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(check, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;

            if(!check[cur]){
                check[cur] = true;

                // 다익스트라는 가능한 모든 경우를 탐색하는 알고리즘이기 때문에
                // 그냥 n.end를 저장하는 방식으로는 안 되고
                // 경로찾기를 하려면 부모노드의 값을 저장하는 추가 배열이 필요하다.
                for(Node n : nodeList.get(cur)){
                    if(!check[n.end] && dist[n.end] > dist[cur] + n.weight){
                        dist[n.end] = dist[cur] + n.weight;
                        pq.add(new Node(n.end, dist[n.end]));
                        // n.end의 이전 값은 cur 라는 뜻
                        parent[n.end] = cur;
                    }
                }
            }
        }
        return dist[end];
    }

    public static String printTrace(int start, int end){
        StringBuilder sb = new StringBuilder("\n");
        Deque<Integer> s = new LinkedList<>();
        int cur = end;
        while(cur != start){
            s.addFirst(cur);
            cur = parent[cur];
        }
        s.addFirst(start);

        sb.append(s.size()).append("\n");

        while(!s.isEmpty()){
            sb.append(s.pollFirst());
            if(!s.isEmpty())
                sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        check = new boolean[N+1];
        dist = new int[N+1];
        // 경로 추적을 위해 부모노드의 값을 저장하는 parent 배열
        parent = new int[N+1];
        nodeList = new ArrayList<>();

        for(int nn = 0; nn <= N; nn++){
            nodeList.add(new ArrayList<>());
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodeList.get(s).add(new Node(e,w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = dijkstra(start, end);

        bw.write(answer+printTrace(start,end));
        bw.flush();
        bw.close();
        br.close();
    }
}