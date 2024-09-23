import java.io.*;
import java.util.*;
/*
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .


ABDCEFG
DBAECFG
DBEGFCA
 */
class Node{
    char val;
    Node left;
    Node right;

    Node(char val, Node left, Node right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    private static StringBuilder sb;
    public static void preOrder(Node cur){
        if(cur == null)
            return;
        sb.append(cur.val);
        preOrder(cur.left);
        preOrder(cur.right);
    }
    public static void inOrder(Node cur){
        if(cur == null)
            return;
        inOrder(cur.left);
        sb.append(cur.val);
        inOrder(cur.right);
    }
    public static void postOrder(Node cur){
        if(cur == null)
            return;
        postOrder(cur.left);
        postOrder(cur.right);
        sb.append(cur.val);
    }
    // 노드 저장 방식은 루트 노드는 한개라서
    // 거기서 시작해서 현재 입력하려는 값의 루트 값이 현재 노드의 루트와 같은지 봐야하고
    // left와 right 처리해줘야함
    public static void addNode(Node node, char root, char left, char right){
        if(node.val == root){
            node.left = (left == '.' ? null : new Node(left, null, null));
            node.right = (right == '.' ? null : new Node(right, null, null));
        }
        else{
            if(node.left != null)
                addNode(node.left, root, left, right);
            if(node.right != null)
                addNode(node.right, root, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        // 루트 노드는 A로 지정되어 있으므로
        // 미리 start Node를 설정해줌
        Node start = new Node('A', null, null);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        while(N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            addNode(start, st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        }
        // A = 1, B = 2, C = 3, ...

        // pre-order
        // root - L - R
        sb = new StringBuilder();
        preOrder(start);
        bw.write(sb + "\n");

        // in-order
        // L - root - R
        sb = new StringBuilder();
        inOrder(start);
        bw.write(sb + "\n");

        // post-order
        // L - R - root
        sb = new StringBuilder();
        postOrder(start);
        bw.write(sb + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}