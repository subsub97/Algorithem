# 분할정복을 이용해서 문제 풀어보기
A,B,C = map(int,input().split())
# 나누기 분배법칙 (A X B) % C = ((A % C) X (A % C)) % C
def find_mode(A,num):
    if num == 1:
        return A % C
    else:
        sort_num = find_mode(A,num // 2)
        if num % 2 != 0: # 홀수인 경우
            return (sort_num * sort_num * A) % C
        else: # 짝수인 경우
            return (sort_num * sort_num) % C
print(find_mode(A,B))


