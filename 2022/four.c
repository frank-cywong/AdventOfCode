#include <stdio.h>
#include <string.h>

char cont(int a, int b, int c, int d){
	if(b < c || d < a){
		return 0;
	}
	return 1;
}

int main(){
	FILE *fs = fopen("four.in", "r");
	int sum = 0;
	int temp;
	char buff[256];
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			int a, b, c, d;
			sscanf(buff, "%u-%u,%u-%u", &a, &b, &c, &d);
			if(cont(a, b, c, d)){
				++sum;
			}
		}
	}
/*
	for (auto rit=nums.rbegin(); rit != nums.rend(); ++rit){
		printf("%d ", *rit);
	}
*/
/*
	int s2 = 0;
	auto rit = nums.rbegin();
	for(int i = 0; i < 3; ++i){
		s2 += *rit;
		++rit;
	}
*/
	printf("%d\n", sum);
	return 0;
}
