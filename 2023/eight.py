import math

# testing input?
TEST_IN = False

f_in_str = "eight_in.txt"
f_test_str = "eight_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

instr = c[0]
c = c[2:]

d = {}

ea = []

for s in c:
    s0 = s[0:3]
    s1 = s[7:10]
    s2 = s[12:15]
    l = [s1, s2]
    d[s0] = l
    if(s0[2] == "A"):
        ea.append(s0)

def gl(x):
    c = x
    l = 0
    while(c[2] != "Z"):
        for v in instr:
            if(v == "R"):
                c = d[c][1]
            else:
                c = d[c][0]
            l += 1
            if(c[2] == "Z"):
                break
    return l

clcm = 1

for eav in ea:
    clcm = math.lcm(clcm, gl(eav))

print("Part 2: {}".format(clcm))