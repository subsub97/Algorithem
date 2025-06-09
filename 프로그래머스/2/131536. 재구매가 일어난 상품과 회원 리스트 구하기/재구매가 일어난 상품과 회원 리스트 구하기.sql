SELECT user_id, product_id
FROM ONLINE_SALE o
group by user_id, product_id
having count(*) >= 2
order by o.user_id asc, o.product_id desc;