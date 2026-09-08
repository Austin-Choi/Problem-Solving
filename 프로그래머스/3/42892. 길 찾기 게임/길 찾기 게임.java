import java.util.*;
/*
9
[1,3] [2,2] [3,5] [5,3] [6,1] [7,2] [8,6] [11,5] [13,3]
1     2     3     4     5     6     7     8      9
6     9     4     1     5     8     7     2      3
-> 입력시 원래 번호가 노드 번호 고정임
-> y좌표는 level
y좌표 큰 순서대로 정렬해서 tree 만들고 dfs 순회결과 print?

*/

class Solution {
    class Node{
        int x,y,num;
        Node left, right;
        
        Node(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.num = n;
        }
    }
    
    void insert(Node p, Node c){
        if(c.x < p.x){
            if(p.left == null)
                p.left = c;
            else
                insert(p.left, c);
        }
        else{
            if(p.right == null)
                p.right = c;
            else
                insert(p.right, c);
        }
    }
    
    void pre(Node cur, ArrayList<Integer> rst){
        if(cur == null)
            return;
        
        rst.add(cur.num);
        pre(cur.left, rst);
        pre(cur.right, rst);
    }
    
    void post(Node cur, ArrayList<Integer> rst){
        if(cur == null)
            return;
        
        post(cur.left, rst);
        post(cur.right, rst);
        rst.add(cur.num);
    }
    
    public int[][] solution(int[][] A) {
        Node[] nodes = new Node[A.length];
        for(int i = 0; i<A.length; i++){
            nodes[i] = new Node(A[i][0], A[i][1], i+1);
        }
        // y좌표 기준으로 정렬 (레벨 순)
        // 같으면 x좌표 작은 순으로 정렬
        Arrays.sort(nodes, (a,b)->{
            if(a.y != b.y)
                return b.y - a.y;
            return a.x - b.x;
        });
        
        // 레벨 제일 높은거 하나는 이진트리에서 루트노드임
        Node root = nodes[0];
        // 나머지 삽입해줌
        for(int i = 1; i<A.length; i++){
            insert(root, nodes[i]);
        }
        
        ArrayList<Integer> prel = new ArrayList<>();
        ArrayList<Integer> postl = new ArrayList<>();
        
        pre(root, prel);
        post(root, postl);
        
        int[][] ans = new int[2][A.length];
        for(int i = 0; i<A.length; i++){
            ans[0][i] = prel.get(i);
            ans[1][i] = postl.get(i);
        }
        
        return ans;
    }
}