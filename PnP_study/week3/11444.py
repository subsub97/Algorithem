
n = int(input())

mode_num = 1000000007
# 덧셈 나머지 분배 법칙 ((A % p) + (B % p)) % p

def find_fibonacci_num(n,cnt):
    while cnt <= n:
        if cnt == 1:
            first_num = cnt % mode_num
            second_num = 0
            cnt += 1
            #return first_num,second_num
        else:
            temp = first_num
            first_num = (first_num + second_num) % mode_num
            second_num = temp
            cnt +=1
    return first_num
answer = find_fibonacci_num(n,1)
print(answer)