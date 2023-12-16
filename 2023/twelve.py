import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = True

f_in_str = "twelve_in.txt"
f_test_str = "twelve_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

def bit_count(self):
    return bin(self).count("1")

def go_test(s, a):
    ccv = 0
    iv = 0
    for c in s:
        if(c == "."):
            if(ccv != 0):
                if(a[iv] != ccv):
                    return False
                iv += 1
                ccv = 0
        elif(c == "#"):
            if(iv >= len(a)):
                return False
            ccv += 1
    if(ccv != 0):
        if(a[iv] != ccv):
            return False
        iv += 1
        ccv = 0
    if(iv != len(a)):
        return False
    return True

def get_cnt(s):
    s = s.split(" ")
    #print(s)
    specs = s[1]
    s = s[0]
    specs = [int(v) for v in specs.split(",")]
    total_v = sum(specs)
    print(total_v)
    bv = 0
    qind = [];
    for i in range(len(s)):
        if(s[i] == "?"):
            qind.append(i)
        if(s[i] == "#"):
            bv += 1
    rv = total_v - bv;
    if(rv < 0 or rv > len(qind)):
        return 0
    cc = 0
    dvs = {}
    for bitmask in range(2 ** len(qind)):
        if(bit_count(bitmask) != rv):
            continue
        picks = [qind[i] for i in range(len(qind)) if (bitmask & (1 << i) != 0)];
        #print(picks)
        s2 = ""
        for i in range(len(s)):
            if s[i] != "?":
                s2 += s[i]
            elif i in picks:
                s2 += "#"
            else:
                s2 += "."
        '''
        for i in range(len(qind)):
            refv = qind[i]
            s2[i] = ("#" if refv in picks else ".")
        '''
        #print(s2)
        if(go_test(s2, specs)):
            cc += 1
    print(cc)
    return cc

sv = 0

for s in c:
    sv += get_cnt(s);

print(f"Part 1 {sv}")