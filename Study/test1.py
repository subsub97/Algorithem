import sys

student_arr = []
n = int(input())

for i in range(n):
    student_arr = list(map(int,sys.stdin.readline().split()))
    sum_val = sum(student_arr)
    sum_val -= student_arr[0]
    average = sum_val/student_arr[0]
    cnt = 0
    for j in range(1,student_arr[0]+1):
        if student_arr[j] >  average:
            cnt += 1

    print(f"{(cnt/student_arr[0])*100:.3f}%")