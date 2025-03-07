SELECT f.CATEGORY AS CATEGORY, f.PRICE AS MAX_PRICE, f.PRODUCT_NAME
FROM FOOD_PRODUCT f
WHERE f.price IN ( 
    SELECT MAX(PRICE)
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    
)
AND f.CATEGORY IN ('과자', '국', '식용유', '김치')
ORDER BY f.PRICE DESC;


