/*
파일 트리를 HashMap으로 인덱싱
cur = cur.children.get(next)로 O(1)에 머지 가능

같은 폴더 안에 같은 이름 없어서 dirMap의 name이 ID역할
-> 일반 트리 문제는 노드의 데이터가 중점이라 유일하지 않아서 map적용 x, dfs로

Node - String name, boolean isFile, List<Node> children
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int K;
    static int Q;
    static Map<String, Node> dirMap = new HashMap<>();
    static class Node{
        String name;
        boolean isFile;
        Map<String, Node> children;
    }

    // 버그 : 카운트 안오름 -> int[1] 짜리 배열로 파라미터 넣음
    static void dfsCount(Node cur, Set<String> kind, int[] cnt){
        for(Node n : cur.children.values()){
            if(n.isFile){
                cnt[0]++;
                kind.add(n.name);
            }
            else
                dfsCount(n, kind, cnt);
        }
    }
    static StringBuilder sb = new StringBuilder();
    static void query(Node n){
        Set<String> kind = new HashSet<>();
        int[] cnt = new int[1];
        dfsCount(n, kind, cnt);
        sb.append(kind.size()).append(" ").append(cnt[0]).append("\n");
    }
    static Node findNode(String s){
        String[] path = s.split("/");
        Node cur = root;
        for(int i = 1; i<path.length; i++){
            cur = cur.children.get(path[i]);
        }
        return cur;
    }
    static Node root;

    // 버그 2 : parent가 null일수 있음
    // 부모부터 입력되지 않을 수 있음
    static Node getOrCreate(String name){
        if(!dirMap.containsKey(name)){
            Node n = new Node();
            n.name = name;
            n.isFile = false;
            n.children = new HashMap<>();
            dirMap.put(name, n);
        }
        return dirMap.get(name);
    }
    static void insert(String P, String F, int C){
        Node parent = getOrCreate(P);
        boolean isFile = (C == 0);

        Node node;
        if(isFile){
            node = new Node();
            node.name = F;
            node.isFile = true;
        } else {
            node = getOrCreate(F);
        }

        parent.children.put(F, node);
    }

    static void merge(String a, String b){
        Node A = findNode(a);
        Node B = findNode(b);

        for(Node child : new ArrayList<>(A.children.values())){
            // 겹치는 폴더이름 있는지
            Node e = B.children.get(child.name);

            if(child.isFile)
                B.children.put(child.name, child);
            else{
                if(e == null)
                    B.children.put(child.name, child);
                else
                    mergeFolder(child, e);
            }
        }
        A.children.clear();
    }

    static void mergeFolder(Node A, Node B){
        for(Node child : new ArrayList<>(A.children.values())){
            Node e = B.children.get(child.name);

            if(child.isFile)
                B.children.put(child.name, child);
            else{
                if(e == null){
                    B.children.put(child.name, child);
                }
                else{
                    mergeFolder(child, e);
                }
            }
        }
        A.children.clear();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        root = new Node();
        root.name = "main";
        root.isFile = false;
        root.children = new HashMap<>();
        dirMap.put("main", root);

        for(int i = 0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            String p = st.nextToken();
            String f = st.nextToken();
            int c = Integer.parseInt(st.nextToken());
            insert(p,f,c);
        }
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            merge(a,b);
        }
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i<Q; i++){
            query(findNode(br.readLine()));
        }
        System.out.print(sb);
    }
}
