cnt = 0
while True:

    if cnt > 3:
        break

    a = int(input())

    if a % 2 == 0:
        print(a // 2)
        cnt += 1



