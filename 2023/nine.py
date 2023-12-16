import math

# testing input?
TEST_IN = False

f_in_str = "nine_in.txt"
f_test_str = "nine_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

sv = 0

for s in c:
    ns = s.split(" ")
    ns = [int(v) for v in ns]
    fds = []
    fds.append(ns)
    cfd = 0
    while(True):
        allzero = True
        cfda = []
        for i in range(len(fds[cfd]) - 1):
            cfda.append(fds[cfd][i+1] - fds[cfd][i])
            if(cfda[i] != 0):
                allzero = False
        cfd += 1
        fds.append(cfda)
        if(allzero):
            break
    for i in range(len(fds) - 2, -1, -1):
        fds[i].insert(0, fds[i][0] - fds[i+1][0])
    #print(fds)
    sv += fds[0][0]

print(sv)