n = int(input())

price_list = tuple(map(int,input().split()))

day1,day2 = map(int,input().split())

gap = -1000000

for j in range(day1, day2):
    if price_list[j] - price_list[day1-1] > gap:
        gap = price_list[j] - price_list[day1-1]
        ansday1 = day1
        ansday2 = j + 1
        buy = price_list[day1-1]
        sell = price_list[j]

for i in range(day1,day2):
    if buy < price_list[ansday1]:
        ansday1 = day1
        buy = price_list[day1]
        gap = price_list[ansday2] - price_list[day1]



if gap <= 0:
    print(-1)

else:
    print(gap)
    print(ansday1,buy)
    print(ansday2,sell)

