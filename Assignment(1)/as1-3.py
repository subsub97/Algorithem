##### 이방법은 O(n^2) 입니다.
n = int(input())

price_list = tuple(map(int,input().split()))

# 주식 거래를 할 날짜를 받는 변수 생성
s_day,e_day = map(int,input().split())

# 주식 이익,손실을 측정하는 변수
gap = -1
ansday1 = 0
ansday2 = 0
buy = price_list[s_day]
sell = 0

for i in range(s_day - 1 , e_day):
    for j in range(i + 1, e_day):
        #이게 키포인트
        if price_list[i] > buy:
            break
        if price_list[j] - price_list[i] > gap:
            gap = price_list[j] -price_list[i]
            ansday1 = i + 1
            ansday2 = j + 1
            buy = price_list[i]
            sell = price_list[j]

        elif gap == price_list[j] - price_list[i]:
            if price_list[i] < buy:
                ansday1 = i + 1
                ansday2 = j + 1
                buy = price_list[i]
                sell = price_list[j]

if gap <= 0:
    print(-1)

else:
    print(gap)
    print(ansday1,buy)
    print(ansday2,sell)



