-- 코드를 입력하세요
--  자동차 종류가 '세단' 또는 'SUV' 인 자동차 중
-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고
-- 불가능하려면? end_date > 11월 1일
-- start_date < 11월 30일
-- 30일간의 대여 금액이 50만원 이상 200만원 미만인
-- 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE)
select a.car_id, a.car_type, round(daily_fee * 30 * (100-discount_rate)/100) as 'FEE'
from car_rental_company_car a
join car_rental_company_rental_history b on a.car_id = b.car_id
join car_rental_company_discount_plan c on a.car_type = c.car_type
where c.duration_type = '30일 이상' 
    and a.car_id not in
    (
    select car_id
    from car_rental_company_rental_history 
    where date_format(end_date,'%y%m%d')>'221101'
        and date_format(start_date,'%y%m%d')<'221130'
        
    )
group by a.car_id
having a.car_type in('세단','SUV') and FEE between 500000 and 2000000
order by FEE desc, car_type, car_id desc
;