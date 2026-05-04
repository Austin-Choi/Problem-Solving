import java.util.*;

class Solution {
    static class Node{
        Node dot, dash;
        char value;
        boolean isEnd;
    }
    
    Node root = new Node();
    
    void insert(String code, char ch){
        Node node = root;
        for(char c : code.toCharArray()){
            if(c == '.'){
                if(node.dot == null)
                    node.dot = new Node();
                node = node.dot;
            }
            else{
                if(node.dash == null)
                    node.dash = new Node();
                node = node.dash;
            }
        }
        node.isEnd = true;
        node.value = ch;
    }
    
    void build(){
        String[] morseArr = {
            ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
            "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-",
            "..-","...-",".--","-..-","-.--","--.."
        };
        
        for(int i = 0; i<26; i++){
            insert(morseArr[i], (char)('a'+i));
        }
    }
    
    char decode(String token){
        Node node = root;
        for(char c : token.toCharArray()){
            if(c == '.')
                node = node.dot;
            else
                node = node.dash;
        }
        return node.value;
    }
    
    public String solution(String letter) {
        build();
        StringBuilder sb = new StringBuilder();
        String[] tokens = letter.split(" ");
        // 공백구분이라 dp나 dfs로 완탐 안해도됨
        for(String token : tokens){
            sb.append(decode(token));
        }
        return sb.toString();
    }
}