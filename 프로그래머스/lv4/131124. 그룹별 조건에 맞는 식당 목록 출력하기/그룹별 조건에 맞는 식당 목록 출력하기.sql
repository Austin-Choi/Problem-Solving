-- 코드를 입력하세요
-- 조건문중 제일 먼저 나오는걸 안에 넣고
-- 출력문과 정렬은 가장 나중에

select a.member_name as 'MEMBER_NAME',  
    b.review_text as 'REVIEW_TEXT', 
    date_format(b.review_date, '%Y-%m-%d') as 'REVIEW_DATE'
from member_profile a
    join rest_review b on a.member_id = b.member_id
where a.member_id =
(
    select member_id
    from rest_review
    group by member_id
    order by count(*) desc
    limit 1
)
order by b.review_date, b.review_text;