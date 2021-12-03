f = open("Three.in",'r')
data = f.read().split('\n')
for i in range(len(data)-1,-1,-1):
    if(data[i] == ""):
        data.pop(i)
datacopy = data[:]
bitlen = len(data[0])
onecount = 0
zerocount = 0
mostcommon = ""
o2 = 0
co2 = 0
for i in range(bitlen):
    onecount = 0
    zerocount = 0
    for entry in data:
        if(entry[i] == "0"):
            zerocount += 1
        else:
            onecount += 1
    if(zerocount > onecount):
        mostcommon = "0"
    else:
        mostcommon = "1"
    for j in range(len(data)-1,-1,-1):
        if(data[j][i] != mostcommon):
            data.pop(j)
    if(len(data) == 1):
        break
o2 = int(data[0],2)
data = datacopy[:]
for i in range(bitlen):
    onecount = 0
    zerocount = 0
    for entry in data:
        if(entry[i] == "0"):
            zerocount += 1
        else:
            onecount += 1
    if(zerocount > onecount):
        mostcommon = "0"
    else:
        mostcommon = "1"
    for j in range(len(data)-1,-1,-1):
        if(data[j][i] == mostcommon):
            data.pop(j)
    if(len(data) == 1):
        break
co2 = int(data[0],2)
print(o2*co2)