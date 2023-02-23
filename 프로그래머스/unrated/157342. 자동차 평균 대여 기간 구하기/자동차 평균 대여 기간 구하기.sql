-- 코드를 입력하세요
-- 507 history id, 220801 221109
-- timestampdiff(단위, 날짜1, 날짜2)
-- round(반올림 할 숫자, 자릿수(.기준으로 왼쪽은 음수 오른쪽은 양수로 표현
-- example -> 1면 xx.x로 표현됨
-- example2 -> -2면 x00으로 표현됨))

select CAR_ID, 
    round(avg(timestampdiff(day, start_date, end_date))+1,1) as AVERAGE_DURATION
from car_rental_company_rental_history
group by car_id
having AVERAGE_DURATION >= 7
order by AVERAGE_DURATION desc, car_id desc
;