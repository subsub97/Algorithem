# 분할정복을 이용해서 문제 풀어보기

def find_mode(A,num,check_point):
    if check_point ==0:
        check_point = A
    if num % 2 != 0:
        num = num // 2
        if num == 1:
            A = (A * A) * check_point
            return A, check_point
        A,check_point = find_mode(A,num,check_point)
        A = (A*A)*check_point
        return A, check_point

    else:
        num = num // 2

    if num == 1:
        A *= A
        return A , check_point
    A,check_point = find_mode(A,num,check_point)
    A *= A
    return A ,check_point

#A,B,C = map(int,input().split())
result,check_point = find_mode(2,100,0)

print(result % 3)
