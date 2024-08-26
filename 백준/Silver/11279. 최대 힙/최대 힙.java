import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<N; i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp == 0){
                if(!pq.isEmpty()){
                    System.out.println(pq.poll());
                }
                else
                    System.out.println(0);
            }
            else{
                pq.add(temp);
            }
        }
    }
}
