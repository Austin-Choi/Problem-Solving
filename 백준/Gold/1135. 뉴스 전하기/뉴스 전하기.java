/*
트리를 만들고 자식 노드중에 자식 처리 시간이 큰 노드부터 먼저 걸면
다른애를 거는 동안 걔가 자기 자식 노드한테 전화를 동시에 걸게 되므로 시간이 단축됨
1) 트리구성-> Node 배열, children에 add
2) dfs로 리프노드까지 내려가서 post order로 소요시간 계산
-> 루트에서 내려오면서 자식 처리시간이 큰 노드부터 dp[부모] +(순위) 로 시간계산
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static class Node{
        int val;
        ArrayList<Node> children;

        Node(int val){
            this.val = val;
            children = new ArrayList<>();
        }
    }
    static int dfs(Node curNode){
        if(curNode.children.isEmpty())
            return 0;

        List<Integer> times = new ArrayList<>();
        for(Node c : curNode.children){
            times.add(dfs(c));
        }

        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        for(int i = 0; i<times.size(); i++){
            maxTime = Math.max(maxTime, times.get(i)+i+1);
        }
        return maxTime;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N];
        for(int i = 0; i<N; i++){
            nodes[i] = new Node(i);
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int p = Integer.parseInt(st.nextToken());
            if(p != -1){
                nodes[p].children.add(nodes[i]);
            }
        }
        
        Node root = nodes[0];
        System.out.println(dfs(root));
    }
}
