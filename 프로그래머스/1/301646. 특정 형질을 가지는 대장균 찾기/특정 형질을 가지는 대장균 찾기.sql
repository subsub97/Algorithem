SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA E
WHERE (E.GENOTYPE & 2) = 0 AND (E.GENOTYPE & 5) > 0;