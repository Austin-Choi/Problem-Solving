-- 코드를 입력하세요
select year, 
    month,
    count(user_id) as puchased_users,
-- 전체 2022 회원 (분모, 구매이력 없는 회원도 포함해서 count)
    round(count(user_id)/(select count(user_id)
    from user_info
    where date_format(joined, '%y') = '21'), 1) as puchased_ratio
from 
(
-- distinct는 한번만 명시
-- 상품을 구매한 2022 회원 (분자, 중복 구매를 제외하고 count)
    select distinct year(b.sales_date) as year, month(b.sales_date) as month, a.user_id
        from online_sale b join user_info a
            on a.user_id = b.user_id and date_format(a.joined, '%y') = '21'
) a
-- 연별로, 달별로
group by year, month
order by year, month
