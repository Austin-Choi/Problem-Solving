-- 코드를 입력하세요
-- join은 수평결합
-- union은 수직결합
-- UNION (DISTINCT)
-- 쿼리의 결과를 합친다. 중복된 ROW는 제거 
-- UNION ALL   
-- 모든 컬럼값이 같은 ROW도 결과로 보여준다. 중복제거 하지 않는다.
select date_format(sales_date, '%Y-%m-%d') as sales_date, product_id, user_id, sales_amount
from online_sale
where date_format(sales_date,'%Y-%m')='2022-03'

union all

select date_format(sales_date, '%Y-%m-%d') as sales_date, product_id, 
-- OFFLINE_SALE 테이블의 판매 데이터의 USER_ID 값은 NULL 로 표시해주세요.
    NULL as user_id, 
    sales_amount
from offline_sale
where date_format(sales_date,'%Y-%m')='2022-03'

order by sales_date, product_id, user_id