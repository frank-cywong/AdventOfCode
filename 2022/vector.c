#include <stdlib.h>
#include <stdio.h>
#include "vector.h"

vector * init_vector(){
	vector *v = malloc(sizeof(vector));
	v->size = 0;
	v->capacity = 10;
	v->p = malloc(sizeof(int) * (v->capacity));
	return v;
}

void vallocate(vector *v, int capacity){
	int *temp = realloc(v->p, sizeof(int) * capacity);
	if(temp){
		v->p = temp;
		v->capacity = capacity;
	} else {
		free(v->p);
		exit(1);
	}
}

void vadd(vector *v, int value){
	if(v->capacity == v->size){ // need resizing
		vallocate(v, v->capacity * 2 + 5);
	}
	v->p[v->size] = value;
	v->size++;
}

void vinsert(vector *v, int index, int value){
	if(v->capacity == v->size){
		vallocate(v, v->capacity * 2 + 5);
	}
	for(int i = v->size - 1; i >= index; i--){
		v->p[i+1] = v->p[i];
	}
	v->p[index] = value;
	v->size++;
}

void vset(vector *v, int index, int value){
	if(index >= 0 && index < v->size){
		v->p[index] = value;
	}
}

int vget(vector *v, int index){
	if(index >= 0 && index < v->size){
		return (v->p[index]);
	}
	return 0;
}

void vdelete(vector *v, int index){
	if(index >= 0 && index < v->size){
		for(int i = index; i < v->size - 1; i++){
			v->p[i] = v->p[i+1];
		}
		v->size--;
	}
}

void vremovelast(vector *v){
	if(v->size >= 1){
		v->size--; // will be overrided eventually, no need to clear
	}
}

vector * vfree(vector *v){
	free(v->p);
	v->capacity = 0;
	v->size = 0;
	return NULL;
}

void vprint(vector *v){
	printf("[");
	for(int i = 0; i < v->size; i++){
		if(i != 0){
			printf(", ");
		}
		printf("%d", v->p[i]);
	}
	printf("]\n");
}

int int_cmp (const void * a, const void * b) {
   return ( *(int*)a - *(int*)b );
}

void vsort(vector *v){
	qsort(v->p, v->size, sizeof(int), int_cmp);
}
