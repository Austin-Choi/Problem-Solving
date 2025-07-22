import java.io.*;
import java.util.Arrays;

public class Main {
    static int T;
    static int N;
    static int count;
    static class Node{
        Node[] children = new Node[10];
        boolean isEnd = false;

        public boolean insert(char[] str, int idx){
            // 끝이면 끝이라고 저장
            if(idx == str.length){
                isEnd = true;
                return hasChild();
            }

            if(isEnd)
                return true;

            int num = str[idx] - '0';
            if(children[num] == null)
                children[num] = new Node();
            return children[num].insert(str, idx+1);
        }

        public boolean hasChild(){
            for(Node child : children){
                if(child != null)
                    return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            count = 0;
            N = Integer.parseInt(br.readLine());
            Node root = new Node();
            boolean hasChild = true;

            String[] nums = new String[N];
            for(int i = 0; i<N; i++){
                nums[i] = br.readLine();
            }

            for(int i = 0; i<N; i++){
                if (root.insert(nums[i].toCharArray(), 0)){
                    hasChild = false;
                    break;
                }
            }

            bw.write(hasChild ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
