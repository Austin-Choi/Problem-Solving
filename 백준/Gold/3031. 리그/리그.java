import java.io.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while(N-->0){
            String in = br.readLine();
            int[] n = new int[5];
            String[] temp = in.split(" ");
            for(int i = 0; i < 5; i++){
                if(temp[i].equals("?"))
                    n[i] = -1;
                else 
                    n[i] = Integer.parseInt(temp[i]);
            }

            int A = 0, B = 0, C = 0;
            boolean found = false;
            outer:
            for(int a = 0; a<=100; a++){
                if(n[1] != -1 && n[1] != a)
                    continue;
                for(int b = 0; b<=(100 - a); b++){
                    if(n[2] != -1 && n[2] != b)
                        continue;

                    // w = 3*a+b
                    if(n[4] != -1 && n[4] != 3*a+b)
                        continue;

                    int c;
                    if(n[0] != -1){
                        c = n[0] - a - b;
                        if(c<0 || a+b+c>100)
                            continue;
                    }
                    // [0] == -1
                    else if(a+b == 100)
                        c = 0;
                    // [0] == -1 && [2] != -1
                    else if(n[3] != -1){
                        c = n[3];
                        if(a+b+c > 100)
                            continue;
                    }
                    else
                        continue;

                    if(n[3] != -1 && c != n[3])
                        continue;

                    A=a;
                    B=b;
                    C=c;
                    break outer;
                }
            }
            n[0] = A+B+C;
            n[1] = A;
            n[2] = B;
            n[3] = C;
            n[4] = 3*A + B;
            

            for(int i = 0; i<5; i++){
                sb.append(n[i]);
                if(i<4)
                    sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}