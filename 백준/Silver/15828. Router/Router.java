import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        while(true){
            int temp = Integer.parseInt(br.readLine());
            if(temp == -1){
                break;
            }
            else if(temp == 0){
                if(!q.isEmpty())
                    q.poll();
            }
            else{
                if(q.size() <= N)
                    q.add(temp);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i : q){
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}