#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "vector.h"

int main(){
	char buff[256];
	printf("Input input file name:\n");
	fgets(buff, 256, stdin);
	buff[strlen(buff) - 1] = '\0';
	FILE * fs = fopen(buff, "r");
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
		}
	}
	return 0;
}
