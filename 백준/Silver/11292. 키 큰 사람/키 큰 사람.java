import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    String n;
    double h;

    public Student(String n, double h){
        this.n = n;
        this.h = h;
    }

    @Override
    public int compareTo(Student o) {
        if(o.h > this.h)
            return 1;
        else if(o.h < this.h)
            return -1;
        else
            return 0;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double ans = 0;
        StringBuilder sb = new StringBuilder();

        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0){
               break;
            }
            ArrayList<Student> ls = new ArrayList<>();
            for(int i = 0; i<N; i++){
               StringTokenizer st = new StringTokenizer(br.readLine());
               ls.add(new Student(st.nextToken(), Double.parseDouble(st.nextToken())));
            }
            Collections.sort(ls);
            ls.add(new Student("d", Double.MAX_VALUE));

            for(int i = 0; i<ls.size()-1; i++){

                sb.append(ls.get(i).n).append(" ");
                if(ls.get(i).h != ls.get(i+1).h)
                    break;
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
