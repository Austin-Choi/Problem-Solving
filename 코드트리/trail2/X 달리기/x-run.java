import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long X = Long.parseLong(br.readLine());

        long k = (long)Math.sqrt(X);

        if (k * k == X) {
            System.out.println(2 * k - 1);
        } else if (X <= k * k + k) {
            System.out.println(2 * k);
        } else {
            System.out.println(2 * k + 1);
        }
    }
}