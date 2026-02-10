# [Gold III] gNumbers (Small) - 12070 

[문제 링크](https://www.acmicpc.net/problem/12070) 

### 성능 요약

메모리: 14224 KB, 시간: 100 ms

### 분류

수학, 다이나믹 프로그래밍, 정수론, 게임 이론, 소수 판정

### 제출 일자

2026년 2월 10일 13:09:08

### 문제 설명

<p>Googlers are crazy about numbers and games, especially number games! Two Googlers, Laurence and Seymour, have invented a new two-player game based on "gNumbers". A number is a gNumber if and only if the sum of the number's digits has no positive divisors other than 1 and itself. (In particular, note that 1 is a gNumber.)</p>

<p>The game works as follows: First, someone who is not playing the game chooses a starting number <strong>N</strong>. Then, the two players take turns. On a player's turn, the player checks whether the current number C is a gNumber. If it is, the player loses the game immediately. Otherwise, the player chooses a prime factor P of C, and keeps dividing C by P until P is no longer a factor of C. (For example, if the current number were 72, the player could either choose 2 and repeatedly divide by 2 until reaching 9, or choose 3 and repeatedly divide by 3 until reaching 8.) Then the result of the division becomes the new current number, and the other player's turn begins.</p>

<p>Laurence always gets to go first, and he hates to lose. Given a number <strong>N</strong>, he wants you to tell him which player is certain to win, assuming that both players play optimally.</p>

### 입력 

 <p>The first line of the input gives the number of test cases, <strong>T</strong>. <strong>T</strong> test cases follow; each consists of a starting number <strong>N</strong>.</p>

### 출력 

 <p>For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1) and y is the winner's name: either <code>Laurence</code> or <code>Seymour</code>.</p>

