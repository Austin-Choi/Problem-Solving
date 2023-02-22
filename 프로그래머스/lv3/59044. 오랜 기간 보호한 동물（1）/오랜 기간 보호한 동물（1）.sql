-- 코드를 입력하세요
-- 못나간 동물은 outs에서 animal_id가 null
-- 오래 있었던 -> 적은 시간대부터 order하고 limit주기
select a.name, a.datetime
from animal_ins a
    left join animal_outs b
    on a.animal_id = b.animal_id
where b.animal_id is null
order by a.datetime asc
limit 3;