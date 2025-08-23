/*
트라이 구성할때
리프노드인지 구분하는 isEnd 꼭 추가하기

루트부터 트리를 따라 내려가며
첫 입력이면 입력해야 하니까 input = true;
children이 1 이상이면 입력해야 하니까 input = true;

트라이에서 단어가 끝나는 지점도 입력해야하는건
접두사 문제 때문임 
ab와 abc 가 있으면 
a,b를 치면 ab가 되지만
그 상태에서 abc랑 ab가 있으면 이게 abc로 갈지 말지 애매함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static double ans = 0.0;
    static class Node{
        Map<Character, Node> children = new HashMap<>();
        boolean isEnd = false;

        void insert(String path, int idx){
            if(idx >= path.length()) {
                isEnd = true;
                return;
            }
            char next = path.charAt(idx);
            children.putIfAbsent(next, new Node());
            children.get(next).insert(path, idx+1);
        }

        int count(String word, int idx, boolean first){
            if(idx == word.length())
                return 0;

            char c = word.charAt(idx);
            Node next = children.get(c);

            boolean needInput = false;
            if(first)
                needInput = true;
            else if(isEnd || children.size()>1)
                needInput = true;

            return (needInput ? 1 : 0) + next.count(word, idx+1, false);

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String temp = br.readLine();
            if(temp == null)
                break;
            N = Integer.parseInt(temp);
            ans = 0;
            Node root = new Node();
            ArrayList<String> li = new ArrayList<>();
            for(int i = 0; i<N; i++){
                String t = br.readLine();
                li.add(t);
                root.insert(t, 0);
            }
            for(String t : li){
                ans += root.count(t, 0, true);
            }
            double answer = ((ans / N) * 100) / 100;
            System.out.printf("%.2f\n", answer);
        }
    }
}
