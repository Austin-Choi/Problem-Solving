/*
N이 몇일때 오답이 9700개 이상 발생하는가
100,
99번 점에서 모든 점들의 최단거리에 영향을 끼치게 하려면 다른 점에서는
엄청 높은 값이고 i == 99 or j == 99에서 최소값 1이어야함
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder("100 \n");
        int N = 100;
        for(int i = 0; i<100; i++){
            for(int j = 0; j<100; j++){
                if(i == j)
                    sb.append("0 ");
                else{
                    if(i == 99 || j == 99)
                        sb.append("1 ");
                    else
                        sb.append("100 ");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
