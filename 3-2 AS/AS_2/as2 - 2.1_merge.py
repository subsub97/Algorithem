n = int(input())
level_list = list(map(int,input().split()))
k = int(input())
max_cnt = 0

for i in range(n):
    cnt = 1
    for j in range(i+1,n):

        if abs(level_list[i] - level_list[j]) <= k:
            cnt += 1
        else:
            if cnt > max_cnt:
                max_cnt = cnt
                print(max_cnt)


print(max_cnt)

