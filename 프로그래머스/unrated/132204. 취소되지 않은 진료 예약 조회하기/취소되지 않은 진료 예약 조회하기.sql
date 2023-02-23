-- 코드를 입력하세요
-- 4월 13일 취소되지 않은 cs
-- 환자 테이블에서 필요한건 pt_name과 예약 테이블 조인에 필요한 pt_no
-- 의사 테이블에서 필요한건 dr_name과 예약 테이블 조인에 필요한 dr_id
select c.apnt_no, a.pt_name, c.pt_no, c.mcdp_cd, b.dr_name, c.apnt_ymd
from appointment c
    join (select pt_name, pt_no from patient) a on c.pt_no = a.pt_no
    join (select dr_name, dr_id from doctor) b on c.mddr_id = b.dr_id
where date_format(c.apnt_ymd,'%m%d') = '0413' and
 c.apnt_cncl_yn = 'N' and c.mcdp_cd = 'CS'
group by apnt_no
order by c.apnt_ymd
;