import time
import random


def compute_e_ver1(n):
# code for O(n^2)-time version
#0번쨰 부터 n번째 항까지 더한다.이때 일반항(k번째일떄)은 1/k! 이다.
    eulerNumber, generalTerm = 1, 0
    for i in range(1,n+1):
        generalTerm = 1 / factorial(i)
        eulerNumber += generalTerm
    return eulerNumber


def compute_e_ver2(n):
    # code for O(n)-time version
    #0번째 부터 n번째 항까지 더한다. 이때 일반항(k번째일떄)은 1/k! 이다.
    e = 1
    fact = 1
    for i in range(1,n+1):
        fact *= i
        e += 1/fact

    return e



def factorial(n):
    result = 1
    if n == 0:
        return 0
    for i in range(1,n+1):
        result *= i
    return result


# n 입력받음
n = int(input())

# compute_e_ver1 호출
ver1_before = time.process_time()
compute_e_ver1(n)
ver1_after = time.process_time()

# compute_e_ver2 호출

ver2_before = time.process_time()
compute_e_ver2(n)
ver2_after = time.process_time()

# 두 함수의 수행시간 출력
print(ver1_after - ver1_before)
print(ver2_after - ver2_before)
