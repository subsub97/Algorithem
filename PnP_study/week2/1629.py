# 분할정복을 이용해서 문제 풀어보기
A,B,C = map(int,input().split())
def find_mode(A,num,check_point):
    if check_point ==0:
        check_point = A

    if num % 2 != 0:
        num = num // 2
        if num == 1:
            A = A % C
            return A, check_point
        A,check_point = find_mode(A,num,check_point)
        A = (A * A) % C
        A = (A * A*(check_point % C)) % C
        return A, check_point

    else:
        num = num // 2

    if num == 1:
        A = A % C
        return A , check_point
    elif num == 3:
        A, check_point = find_mode(A, num, check_point)
        A = (A * A) % C
        A = (A * A * (check_point % C)) % C
        return A, check_point
    else:
        A,check_point = find_mode(A,num,check_point)
        A = (A * A) % C
    return A ,check_point


result,check_point = find_mode(A,B,0)

print(result)
