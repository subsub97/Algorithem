SELECT MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(r.review_date, '%Y-%m-%d')
FROM REST_REVIEW r 
    JOIN (SELECT MEMBER_ID
          FROM rest_review
          GROUP BY member_id
          ORDER BY COUNT(*) DESC LIMIT 1) r1
        ON r.member_id = r1.member_id
    JOIN MEMBER_PROFILE m ON r.member_id = m.member_id

ORDER BY r.review_date, r.review_text