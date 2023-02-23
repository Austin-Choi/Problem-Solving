-- 코드를 입력하세요
-- 소수점 반올림하는 ROUND(숫자, -1) 10의 자리에서 반올림
-- 0이면 소숫점 첫번째 자리에서 반올림 
-- 평균 AVG(구할것들)
SELECT ROUND(AVG(DAILY_FEE),0) AS AVERAGE_FEE
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_TYPE = 'SUV'
;