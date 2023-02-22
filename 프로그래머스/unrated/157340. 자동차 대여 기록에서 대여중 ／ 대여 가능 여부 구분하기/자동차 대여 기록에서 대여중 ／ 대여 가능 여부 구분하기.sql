-- 코드를 입력하세요
-- case문 사용법 
-- case로 시작, when으로 조건절, then 조건절이 참일 때 실행할 것, else 조건절이 거짓일때 실행, end로 종료
-- max를 쓰는 이유는
-- 같은 id를 가지는 차가 여러 대여 이력이 있을 때, 최근 이력으로 가져와야 하므로
-- max(날짜) -> 최근, min(날짜) -> 가장 오래된
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