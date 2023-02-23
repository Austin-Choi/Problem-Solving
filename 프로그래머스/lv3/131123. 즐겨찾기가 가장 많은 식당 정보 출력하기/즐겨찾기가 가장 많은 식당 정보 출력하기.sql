-- 코드를 입력하세요
select food_type, rest_id, rest_name, favorites
from rest_info
-- 그냥 서브쿼리 없이 처리하면 max(fav)에 동일한 값이 여러개 나올수 있어서 
-- where 절에서 처리
where (food_type, favorites) in
    (select food_type, max(favorites)
    from rest_info
    group by food_type)
group by food_type
order by food_type desc
;