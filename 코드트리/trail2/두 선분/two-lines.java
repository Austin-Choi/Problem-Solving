import java.util.*;
import java.io.*;

/*
끝점 기준으로 정렬하고 0의 끝 >= 1의 시작 이면 겹침
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        ArrayList<int[]> li = new ArrayList<>();
        li.add(new int[]{read(), read()});
        li.add(new int[]{read(), read()});
        Collections.sort(li, (a,b)->{
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[0]-b[0];
        });
        String s = "intersecting";
        if(li.get(0)[1] < li.get(1)[0])
            s = "non"+s;
        System.out.print(s);
    }
}