SELECT
    r.rest_ID,
    r.REVIEW_SCORE,
    CASE
        WHEN r.REVIEW_SCORE = 5 THEN r.MEMBER_ID
        ELSE
            CONCAT(r.REST_ID, ' and ', (
                SELECT COUNT(*) -1 FROM REST_REVIEW AS sub
                WHERE sub.REVIEW_SCORE = r.REVIEW_SCORE
            ), ' other ')
    END AS REVIEW
FROM REST_REVIEW AS r
GROUP BY r.REVIEW_SCORE;