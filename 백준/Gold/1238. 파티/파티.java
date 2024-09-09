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
    private static boolean[] check;
    private static int[] dist;
    private static int N;
    private static int M;
    private static int X;

    private static ArrayList<ArrayList<Node>> nodeList;

    public static int dijkstra(int start, int end){
        Arrays.fill(check, false);
        //  M 10,000 * weight 100 = 1,000,000
        Arrays.fill(dist, 1000000);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;

            if(!check[cur]){
                check[cur] = true;

                for(Node n : nodeList.get(cur)){
                    if(!check[n.end] && dist[n.end] > dist[cur] + n.weight){
                        dist[n.end] = dist[cur] + n.weight;
                        pq.offer(new Node(n.end, dist[n.end]));
                    }
                }

            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // dijkstra 용 거리 갱신 배열
        dist = new int[N+1];
        check = new boolean[N+1];
        nodeList = new ArrayList<>();

        for(int i = 0; i<N+1; i++){
            nodeList.add(new ArrayList<>());
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodeList.get(s).add(new Node(e,w));
        }

        int[] answer = new int[N+1];

        /*
        1~N에서부터 X까지의 값 배열로 저장
        X에서부터 1~N까지의 값 배열로 저장
        각 요소 더하고
        그중 제일 큰 값 출력
         */
        for(int nn = 1; nn <= N; nn++){
            // 집에서 파티장소까지 가는거
            answer[nn-1] = dijkstra(nn, X);
            // 파티장소에서 집까지 오는거
            answer[nn-1] += dijkstra(X, nn);
        }
        PriorityQueue<Integer> ans = new PriorityQueue<>(Collections.reverseOrder());
        for(int n : answer){
            ans.add(n);
        }
        bw.write(ans.poll() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
