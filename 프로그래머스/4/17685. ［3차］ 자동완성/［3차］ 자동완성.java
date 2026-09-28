import java.util.*;

class Solution {
    class Node{
        //boolean isEnd;
        Node[] children;
        // 몇개의 단어가 이 글자를 지나쳐가는지
        int count;
        Node(){
            this.children = new Node[26];
            //this.isEnd = false;
            this.count = 0;
        }
    }
    Node root = new Node();
    void insert(Node cur, String s){
        char[] t = s.toCharArray();
        
        for(char c : t){
            if(cur.children[c-'a'] == null){
                cur.children[c-'a'] = new Node();
            }
            cur = cur.children[c-'a'];
            cur.count++;
        }
        //cur.isEnd = true;
    }
    
    public int solution(String[] words) {
        for(String w : words){
            insert(root, w);
        }
        
        int ans = 0;
        
        for(String w : words){
            Node cur = root;
            
            char[] t = w.toCharArray();
            for(int i = 0; i<t.length; i++){
                char c = t[i];
                cur = cur.children[c-'a'];
            
                // 지금 문자 위치에서 지나가는 단어가 1개임
                // 이걸로 특정할수 있으니 0-based라 i+1
                if(cur.count == 1){
                    ans += i+1;
                    break;
                }
                
                // 문자 끝까지 특정할수 없었고 중복되는 단어가 없으므로
                // 끝까지 쳐서 찾아야함
                if(i == t.length-1){
                    ans += i+1;
                }
            }
        }
        return ans;
    }
}