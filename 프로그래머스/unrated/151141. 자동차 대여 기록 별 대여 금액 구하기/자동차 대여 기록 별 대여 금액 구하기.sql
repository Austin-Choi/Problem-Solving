-- 코드를 입력하세요
-- a.car_id = b.car_id
-- a.car_type = c.car_type
-- a.car_id, a.daily_fee,b.rentaldays, 

select b.history_id as 'HISTORY_ID',
-- 정수부분만 출력
    round((case
     when b.rentaldays < 7 then a.daily_fee * b.rentaldays
     when b.rentaldays < 30 then a.daily_fee * 0.95 * b.rentaldays
     when b.rentaldays < 90 then a.daily_fee * 0.92 * b.rentaldays
     else a.daily_fee * 0.85 * b.rentaldays
     end
    ),0) as 'FEE'
-- select *
-- 테이블 전체 조인보다는 필요한 컬럼만 조인하는게 확인하기 쉬움
-- 할인 테이블은 조인할 필요 없음
-- datediff(enddate, startdate) +1
from (select car_id, car_type, daily_fee from car_rental_company_car where car_type='트럭') a 
    join (select history_id, car_id, datediff(end_date, start_date)+1 as rentaldays
         from car_rental_company_rental_history) b 
    on a.car_id = b.car_id
group by history_id
order by FEE desc, HISTORY_ID desc
;