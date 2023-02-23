-- 코드를 입력하세요
select distinct a.car_id
from car_rental_company_car a
    join car_rental_company_rental_history b on a.car_id = b.car_id
where a.car_type ='세단' and
    date_format(b.start_date, '%m') = '10'
order by a.car_id desc
;