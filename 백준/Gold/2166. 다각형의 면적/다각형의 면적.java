import java.util.Scanner;
 
public class Main {
    
    // Point 클래스: x와 y 좌표를 저장하는 클래스
    static class Point {
        long x, y;
        
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 다각형의 꼭짓점 개수 N을 입력 받음
        int N = sc.nextInt();
        
        // 다각형의 꼭짓점을 저장할 Point 배열 생성
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            long x = sc.nextLong();  // 꼭짓점의 x 좌표를 입력 받음
            long y = sc.nextLong();  // 꼭짓점의 y 좌표를 입력 받음
            points[i] = new Point(x, y);  // 입력 받은 좌표로 Point 객체를 생성하여 배열에 저장
        }
        
        // 다각형의 면적을 계산
        double area = calculatePolygonArea(points);
        
        // 계산된 면적을 소수점 첫째 자리까지 출력
        System.out.printf("%.1f", area);
    }
    
    // 다각형의 면적을 계산하는 함수
    private static double calculatePolygonArea(Point[] points) {
        int n = points.length;  // 다각형의 꼭짓점 개수를 n에 저장
        long area = 0;  // 면적을 저장할 변수 초기화
        
        for (int i = 0; i < n; i++) {
            long x1 = points[i].x;  // 현재 꼭짓점의 x 좌표를 변수에 저장
            long y1 = points[i].y;  // 현재 꼭짓점의 y 좌표를 변수에 저장
            long x2 = points[(i + 1) % n].x;  // 다음 꼭짓점의 x 좌표를 변수에 저장
            long y2 = points[(i + 1) % n].y;  // 다음 꼭짓점의 y 좌표를 변수에 저장
            
            // 각 꼭짓점의 좌표를 이용하여 삼각형의 면적을 계산하고, 이를 전체 면적에 더함
            area += (x1 * y2) - (x2 * y1);
        }
        
        // 계산된 면적의 절댓값을 취한 뒤 2로 나눠 면적을 반환. 절댓값을 취함으로써 항상 양수의 면적을 반환
        return Math.abs(area) / 2.0;
    }
    
}
 