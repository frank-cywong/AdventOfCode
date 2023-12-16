import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = True

f_in_str = "thirteen_in.txt"
f_test_str = "thirteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

sv = 0

a = []

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

def dc(sa, sb):
    rcc = 0
    for i in range(len(sa)):
        if(sa[i] != sb[i]):
            rcc += 1
    return rcc

def get_v(a):
    for l in range(1, len(a)):
        check_t = l - 1
        check_b = l
        diffcnt = 0
        while(check_t >= 0 and check_b < len(a)):
            diffcnt += dc(a[check_t], a[check_b])
            if(diffcnt > 1):
                break
            check_t -= 1
            check_b += 1
        if diffcnt == 1:
            return l
    return 0;

for s in c:
    if(s != ""):
        a.append(s)
    else:
        #print(a)
        rvr = get_v(a)
        if(rvr != 0):
            sv += 100 * rvr
        else:
            b = []
            b = transpose(a, b)
            rvr = get_v(b)
            sv += rvr
        a = []

print(f"Part 1 {sv}")