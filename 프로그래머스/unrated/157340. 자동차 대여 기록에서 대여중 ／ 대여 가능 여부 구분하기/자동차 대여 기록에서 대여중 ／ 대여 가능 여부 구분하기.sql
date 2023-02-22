-- 코드를 입력하세요
-- case문 사용법 
-- case로 시작, when으로 조건절, then 조건절이 참일 때 실행할 것, else 조건절이 거짓일때 실행, end로 종료
SELECT car_id, max(
    case
    when '2022-10-16' between date_format(start_date, '%Y-%m-%d') and date_format(end_date, '%Y-%m-%d')
    then '대여중'
    else '대여 가능'
    end
) as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
order by car_id desc
;