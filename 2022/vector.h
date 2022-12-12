#ifndef VECTOR_H
#define VECTOR_H

typedef struct Vector{
	int size;
	int *p;
	int capacity;
} vector;

vector * init_vector();

void vallocate(vector *v, int capacity);

void vadd(vector *v, int value);

void vinsert(vector *v, int index, int value);

void vset(vector *v, int index, int value);

int vget(vector *v, int index);

void vdelete(vector *v, int index);

void vremovelast(vector *v);

vector * vfree(vector *v);

void vprint(vector *v);

void vsort(vector *v);

#endif
