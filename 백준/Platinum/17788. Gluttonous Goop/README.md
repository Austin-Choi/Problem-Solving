# [Platinum V] Gluttonous Goop - 17788 

[문제 링크](https://www.acmicpc.net/problem/17788) 

### 성능 요약

메모리: 30416 KB, 시간: 204 ms

### 분류

애드 혹, 너비 우선 탐색, 그래프 이론, 그래프 탐색, 구현, 수학, 시뮬레이션

### 제출 일자

2025년 9월 15일 09:01:53

### 문제 설명

<p>As a prominent researcher in the laboratorium for Breathtaking Agriculture through Petri dish Cultivation, you keep on looking for new organisms that might be the food of the future. Recently you have discovered a new fungus-type organism that seems to be nutritious and very efficient in converting energy from food to body mass. You have placed a small batch surrounded by food in a petri dish and watched it grow for a bit.</p>

<p>However, now that the weekend has arrived, you would rather spend some time with your loved ones than stare at the contents of this petri dish all the time (even though it is a fun guy). You cannot leave without taking the necessary precautions. What if the fungus grows too large and starts eating away at the rest of the laboratory?!</p>

<p>You model the situation as follows: you divide the plane into 1 × 1-squares, and draw where the fungus currently is. You know that every time step, if the fungus occupies a square, it will expand to all eight neighbouring squares (and still occupy the initial square). You know how many time steps you will be gone for over the weekend, and now you want to know how many squares the fungus will occupy when you get back.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/f2888288-07de-437a-900e-d43887a8f488/-/preview/" style="width: 486px; height: 148px;"></p>

<p>Figure G.1: Example of fungus growth: the fungus from sample 2 after 0, 1, 2 time steps. The middle image corresponds to the correct output for sample 2.</p>

<p>N.B.: In the input, the fungus will be given on a finite grid, but it can (and will!) grow beyond these boundaries. The fungus is not so easily contained.</p>

### 입력 

 <ul>
	<li>First a line containing integers 1 ≤ r, c ≤ 20, and 0 ≤ k ≤ 10<sup>6</sup>, denoting the number of rows and columns of the initial grid and the number of time steps.</li>
	<li>Then follow r lines of c characters, each character being either ‘.’ or ‘#’. A ‘#’ denotes that the fungus is occupying this square. The fungus need not be connected.</li>
</ul>

### 출력 

 <ul>
	<li>Output the number of squares the fungus occupies after k time steps have passed.</li>
</ul>

