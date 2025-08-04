import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static class Node{
        String dir;
        SortedMap<String, Node> children = new TreeMap<>();
        boolean isEnd = false;

        Node(String s){
            this.dir = s;
        }
    }
    static class Directory{
        Node root = new Node("");

        public void insert(String path){
            String[] parts = path.split("\\\\");
            Node node = root;
            for(String part : parts){
                if(part.isEmpty())
                    continue;
                node.children.putIfAbsent(part, new Node(part));
                node = node.children.get(part);
            }
            node.isEnd = true;
        }

        public StringBuilder print(Node curRoot, int depth, StringBuilder sb){
            for(String child : curRoot.children.keySet()){
                Node cur = curRoot.children.get(child);
                for(int i = 0; i<depth; i++){
                    sb.append(" ");
                }
                sb.append(cur.dir).append("\n");
                sb = print(cur, depth+1, sb);
            }
            return sb;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Directory trie = new Directory();
        for(int n = 0; n<N; n++){
            String path = br.readLine();
            trie.insert(path);
        }
        StringBuilder sb = new StringBuilder();

        bw.write(trie.print(trie.root, 0, sb).toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
