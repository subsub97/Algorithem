SELECT
    i.rest_id,
    i.rest_name,
    i.food_type,
    i.favorites,
    i.address,
    ROUND(AVG(r.review_score), 2) AS score
FROM
    rest_info i
    JOIN rest_review r ON i.rest_id = r.rest_id
WHERE
    i.address LIKE '서울%'  -- LIKE 최적화 필요 시 FULLTEXT 또는 지역 테이블 분리 고려
GROUP BY
    i.rest_id,
    i.rest_name,
    i.food_type,
    i.favorites,
    i.address
ORDER BY
    score DESC,
    i.favorites DESC;
