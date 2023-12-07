SHELL := /bin/bash

OBJECTS= vector.o
CFLAGS= -Wall -Wextra
LDFLAGS= -lm

compile: $(DAY).c $(OBJECTS)
	gcc -c $(CFLAGS) $(DAY).c
	gcc -o $(DAY).elf $(DAY).o vector.o $(LDFLAGS)
	./$(DAY).elf

stub: base-c.c
	cp base-c.c $(DAY).c
	notepad++.exe $(DAY).c

vector.o: vector.c vector.h
	gcc -c $(CFLAGS) vector.c
