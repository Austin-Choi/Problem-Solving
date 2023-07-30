-- 코드를 입력하세요
select dr_name, dr_id, mcdp_cd, to_char(hire_ymd, 'YYYY"-"MM"-"DD') as HIRE_YMD
from doctor
where mcdp_cd in ('CS', 'GS')
order by hire_ymd desc, dr_name;
