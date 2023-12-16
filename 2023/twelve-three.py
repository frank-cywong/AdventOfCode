import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

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

def get_combo_from_s(s):
    a = []
    ccv = 0
    for c in s:
        if(c == "."):
            if(ccv != 0):
                a.append(str(ccv))
                ccv = 0
        else:
            ccv += 1
    if(ccv != 0):
        a.append(str(ccv))
    return ",".join(a)

def get_all_combos(s):
    qind = [];
    dict_combos_freq = {}
    for i in range(len(s)):
        if(s[i] == "?"):
            qind.append(i)
    for bitmask in range(2 ** len(qind)):
        picks = [qind[i] for i in range(len(qind)) if (bitmask & (1 << i) != 0)];
        s2 = ""
        for i in range(len(s)):
            if s[i] != "?":
                s2 += s[i]
            elif i in picks:
                s2 += "#"
            else:
                s2 += "."
        #print(s2)
        sa = get_combo_from_s(s2)
        if sa in dict_combos_freq:
            dict_combos_freq[sa] += 1
        else:
            dict_combos_freq[sa] = 1
    return dict_combos_freq

def no_zeros(s):
    for c in s:
        if(c == "."):
            return False
    return True

def get_cnt(s):
    s = s.split(" ")
    #print(s)
    specs = s[1]
    s = s[0]
    specs = [int(v) for v in specs.split(",")]
    sx = ""
    for i in range(5):
        if(i != 0):
            sx += "?"
        sx += s
    s = sx
    specs = specs * 5
    print(s)
    print(specs)
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
    dp = [];
    #dp[i][j]: use 1st i chars but not i, place first j-1 specs
    for i in range(len(s) + 1):
        l = [0] * (len(specs) + 1)
        dp.append(l)
    dp[0][0] = 1
    for i in range(1, len(s) + 1):
        for j in range(0, len(specs) + 1):
            if(s[i-1] == "."):
                dp[i][j] = dp[i-1][j]
                continue
            pound_val = 0
            if(j > 0):
                curspec = specs[j-1]
                if(i >= curspec):
                    if(i == curspec):
                        if(no_zeros(s[0:i])):
                            pound_val = dp[0][j-1]
                    else:
                        # need . right before
                        tv = i - curspec - 1
                        if(s[tv] != "#"):
                            if(no_zeros(s[tv+1:i])):
                                pound_val = dp[tv][j-1]
            if(s[i-1] == "#"):
                dp[i][j] = pound_val;
            else:
                dp[i][j] = pound_val + dp[i-1][j];
    #print(dp)
    print(dp[len(s)][len(specs)])
    return dp[len(s)][len(specs)]
'''
def get_cnt(s):
    s = s.split(" ")
    #print(s)
    specs = s[1]
    s = s[0]
    specs = [int(v) for v in specs.split(",")]
    sx = ""
    for i in range(5):
        if(i != 0):
            sx += "?"
        sx += s
    s = sx
    specs = specs * 5
    print(s)
    print(specs)
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
    segs = [v for v in s.split(".") if len(v) > 0]
    #print(segs)
    lvs = [get_all_combos(v) for v in segs]
    #print(lvs)
    dp = []; # dp[i][j] = # of ways to use i lvs put first j specs
    for i in range(len(segs) + 1):
        l = [0] * (len(specs) + 1)
        dp.append(l)
    #print(dp)
    dp[0][0] = 1
    for i in range(1, len(segs) + 1):
        for j in range(0, len(specs) + 1):
            methods = 0
            for k in range(0, j + 1):
                temps = specs[k:j]
                temps = [str(v) for v in temps]
                temps = ",".join(temps)
                #print(f"CHECKING {temps}")
                if(temps in lvs[i-1]):
                    #print("CHECK PASSED")
                    methods += (dp[i-1][k] * lvs[i-1][temps])
            dp[i][j] = methods
    #print(dp)
    print(dp[len(segs)][len(specs)])
    return dp[len(segs)][len(specs)]
'''

'''
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
        #print(s2)
        if(go_test(s2, specs)):
            cc += 1
    print(cc)
    return cc
'''

sv = 0

for s in c:
    sv += get_cnt(s);

print(f"Part 1 {sv}")