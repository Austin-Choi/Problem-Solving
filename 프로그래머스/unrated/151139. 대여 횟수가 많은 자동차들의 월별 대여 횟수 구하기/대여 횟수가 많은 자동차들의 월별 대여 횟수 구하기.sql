-- 코드를 입력하세요
select month(start_date) as month, car_id, count(history_id) as records
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where car_id in 
    (
        select car_id
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where date_format(start_date, '%Y-%m') between '2022-08' and '2022-11'
        group by car_id
        having count(history_id) >= 5
    )
    and date_format(start_date, '%Y-%m') between '2022-08' and '2022-11'
group by month, car_id
having records > 0
order by month asc, car_id desc;