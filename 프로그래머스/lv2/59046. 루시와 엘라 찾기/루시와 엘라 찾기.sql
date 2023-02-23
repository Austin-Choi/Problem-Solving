-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE STRCMP(NAME, 'Lucy') = 0
    OR STRCMP(NAME, 'Ella') = 0
    OR STRCMP(NAME, 'Pickle') = 0
    OR STRCMP(NAME, 'Rogan') = 0
    OR STRCMP(NAME, 'Sabrina') = 0
    OR STRCMP(NAME, 'Mitty') = 0
;