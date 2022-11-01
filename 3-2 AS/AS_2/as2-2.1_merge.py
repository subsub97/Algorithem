n = int(input())
level_list = list(map(int,input().split()))
k = int(input())
max_cnt = 1

level_list.sort()
print(level_list)

for i in range(n):
    cnt = 1
    for j in range(i+1,n):
        if (level_list[j] - level_list[i] ) <= k:
            cnt += 1
        else:
            if cnt > max_cnt:
                max_cnt = cnt
            break


print(max_cnt)

