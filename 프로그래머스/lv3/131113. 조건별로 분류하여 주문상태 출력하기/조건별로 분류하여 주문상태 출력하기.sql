-- 코드를 입력하세요
-- case문에서 when then 절은 여러개 쓸 수 있으나
-- else는 딱 한번, 위에서 열거된 모든 when 절이 참이 아닐때의 경우이다
select order_id, product_id, date_format(out_date, '%Y-%m-%d') as out_date, 
    (
    case
    when date_format(out_date, '%m%d') > '0501' then '출고대기'
    when date_format(out_date, '%m%d') <= '0501' then '출고완료'
    when out_date is null then '출고미정'
    end
    ) as '출고여부'
from food_order
order by order_id