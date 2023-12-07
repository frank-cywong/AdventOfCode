#include <stdio.h>
#include <string.h>

int main(){
	FILE *fs = fopen("six.in", "r");
	int sum = 0;
	int temp;
	char buff[16384];
	char buff2[14];
	char end = 0;
	while(fgets(buff, 16384, fs)){
		if(buff[0] != '\n' && (!end)){
			int sl = strlen(buff);
			for(int i = 14; i < sl; ++i){
				strncpy(buff2, buff + i - 14, 14);
				char fail = 0;
				for(char *p1 = buff2; p1 < buff2 + 14; p1++){
					for(char *p2 = p1 + 1; p2 < buff2 + 14; p2++){
						if(*p1 == *p2){
							fail = 1;
							break;
						}
					}
					if(fail){
						break;
					}
				}
				if(!fail){
					end = 1;
					printf("%d\n", i);
					break;
				}
			}
		}
	}
	return 0;
}

