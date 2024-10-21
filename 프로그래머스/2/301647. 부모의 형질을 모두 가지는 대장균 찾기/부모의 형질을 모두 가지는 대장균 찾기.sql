select 
    ed.ID, 
    ed.GENOTYPE,
    -- 부모의 gen
    (select ed_2.GENOTYPE from ECOLI_DATA ed_2 where ed_2.ID = ed.PARENT_ID) as 'PARENT_GENOTYPE'
from ECOLI_DATA ed
where
    ed.PARENT_ID is not NULL 
    and
        -- 부모의 gen을 2진수로 변환
        (select ed_3.GENOTYPE from ECOLI_DATA ed_3 where ed_3.ID = ed.PARENT_ID)
        -- 자신의 gen과 비트연산
        & ed.GENOTYPE = (select ed_3.GENOTYPE from ECOLI_DATA ed_3 where ed_3.ID = ed.PARENT_ID)
order by ed.ID
;