-- 코드를 입력하세요
SELECT ai.ANIMAL_ID, ai.NAME
from ANIMAL_INS ai
where ai.INTAKE_CONDITION != 'Aged'
;