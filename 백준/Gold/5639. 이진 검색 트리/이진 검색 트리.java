
import java.util.*;
import java.io.*;
class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
public class Main {
    private static ArrayList<Integer> li = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    private static int idx = 0;

    private static Node makeTree(int min, int max){
        if(idx >= li.size())
            return null;

        int curVal = li.get(idx);

        if(curVal < min || curVal > max)
            return null;

        idx++;
        Node n = new Node(curVal);
        n.left = makeTree(min, curVal);
        n.right = makeTree(curVal, max);

        return n;
    }
    private static void postOrder(Node n){
        if(n.left != null)
            postOrder(n.left);
        if(n.right != null)
            postOrder(n.right);
        sb.append(n.value).append("\n");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            try{
                String s = br.readLine();
                int num = Integer.parseInt(s);
                li.add(num);
            }catch (Exception e){
                break;
            }
        }

        Node root = makeTree(Integer.MIN_VALUE, Integer.MAX_VALUE);
        postOrder(root);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
