import java.io.*;
public class Main {
    static int gcd(int x, int y){
        while(y!=0){
            int temp = x%y;
            x = y;
            y = temp;
        }
        return x;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(":");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);
        int g = gcd(a,b);
        System.out.println(a/g+":"+b/g);
    }
}
