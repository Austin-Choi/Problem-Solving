-- 코드를 입력하세요
-- 두 테이블에 양쪽 모두의 데이터 비교
-- join
-- https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/
select a.animal_id, a.name
from animal_ins a join animal_outs b
    on a.animal_id = b.animal_id
where a.datetime >= b.datetime
order by a.datetime
;