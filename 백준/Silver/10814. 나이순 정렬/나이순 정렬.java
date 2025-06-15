import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
    int age;
    String name;
    int num;

    public Info(int age, String name, int num){
        this.age = age;
        this.name = name;
        this.num = num;
    }

    @Override
    public int compareTo(Info o){
        if(this.age == o.age){
            return this.num - o.num;
        }
        return this.age - o.age;
    }
}
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            pq.add(new Info(age, s, i));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Info cur = pq.poll();
            sb.append(cur.age).append(" ").append(cur.name).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
