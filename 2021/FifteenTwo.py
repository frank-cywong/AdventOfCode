f = open("Fifteen.in")
fs = f.readlines()
output = []
for line in fs:
    nl = line[:-1]
    toappend = ""
    for i in range(5):
        tempnl = ""
        toappend += nl
        for char in nl:
            tempnl += ('1' if char == '9' else str(int(char)+1))
        nl = tempnl[:]
    output.append(toappend)
f.close()
f = open("FifteenTwo.in",'w')
os = "\n".join(output)
os += "\n"
for i in range(5):
    f.write(os)
    tempos = ""
    for char in os:
        if(char == "\n"):
            tempos += char
        else:
            tempos += ('1' if char == '9' else str(int(char)+1))
    os = tempos[:]
    