/*
    4 3 6 8 7 5 2 1 <- 이걸 만드려고 함
    stack 에는 12345678 순서로 push 됨
    1 2 3 4
    1 2 / 4,3
    1 2 5 6 / 4,3
    1 2 5 / 4,3,6
    1 2 5 7 8 / 4,3,6
    / 4,3,6,8,7,5,2,1

    입력 배열에 쭉 넣고
    1~n 순서대로 삽입되니까 start라는 변수에 1넣고 시작
    while(idx<N)[
        input[idx]마다 살펴보면서
        input[idx] + 1보다 작으면 'start' push
        st이 비지 않았을 때 peek했는데 idx의 value랑 같으면 pop
        idx++;
    ]

    1,2,5,3,4

    1
    /1
    2/1
    /1,2
    3,4,5/1,2
    3,4/1,2,5 => target하고 맞지않는데 pool은 비어있음 (NO)

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int start = 1; // 스택에 1부터 순서대로 넣기 위해 시작 숫자 설정

        for(int i = 0; i < n; i++){
            int target = Integer.parseInt(br.readLine());

            // 목표 값이 start보다 크거나 같으면 start부터 목표 값(target)까지 스택에 푸시
            if(target >= start){
                while(start <= target){
                    stack.push(start++);
                    sb.append("+\n");
                }
            }

            // 스택 맨 위의 값이 현재 값과 같으면 pop
            if(stack.peek() == target){
                stack.pop();
                sb.append("-\n");
            } 
            else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb.toString());
    }
}