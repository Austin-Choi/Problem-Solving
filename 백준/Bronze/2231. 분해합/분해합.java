import java.io.*;
public class Main {
    static int getSum(int x){
        int rst = x;
        char[] temp = String.valueOf(x).toCharArray();
        for(int i = 0; i<temp.length; i++){
            rst+= temp[i] - '0';
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int n = N;
        int min = 1_000_001;
        while(n>=0){
            if(getSum(n) == N)
                min = Math.min(min, n);
            n--;
        }
        if(min != 1_000_001)
            System.out.print(min);
        else 
            System.out.print(0);
    }
}
