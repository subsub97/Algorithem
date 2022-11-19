
import sys
sys.setrecursionlimit(10**9)

n = int(input())

mode_num = 1000000007

# 덧셈 나머지 분배 법칙 ((A % p) + (B % p)) % p

def find_fibonacci_num(n):
    if n == 1:
        first_num = n % mode_num
        second_num = 0
        return first_num,second_num

    else:
        first_num, second_num = find_fibonacci_num(n-1)
        return (first_num + second_num) % mode_num,first_num

answer,b = find_fibonacci_num(n)
print(answer)