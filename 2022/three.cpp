#include <cstdio>
#include <cstring>
#include <set>

using namespace std;

int get_priority(char c){
	if(c >= 'a'){
		return c - 'a' + 1;
	}
	return c - 'A' + 27;
}

int main(){
	FILE *fs = fopen("three.in", "r");
	int sum = 0;
	int temp;
	char buff[256];
	//set<char> a;
	int lc = 0;
	bool occ[3][128];
	//set<int> b;
	for(int i = 0; i < 3; ++i){
		for(int j = 0; j < 128; ++j){
			occ[i][j] = false;
		}
	}
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			int sl = strlen(buff);
			for(int i = 0; i < sl; ++i){
				occ[lc][buff[i]] = true;
				//printf("char %c, i %d, sl %d\n", buff[i], i, sl);
				/*
				if(i < (sl / 2)){
					a.insert(buff[i]);
				} else {
					//printf("CHAR: %c\n", buff[i]);
					if(a.find(buff[i]) != a.end()){
						//printf("CHAR MATCH: %c\n", buff[i]);
						sum += get_priority(buff[i]);
						a.clear();
						break;
					}
				}
				*/
			}
			lc++;
			if(lc == 3){
				lc = 0;
				bool found = false;
				for(int c = 'A'; c <= 'z'; ++c){
					if(!found && occ[0][c] && occ[1][c] && occ[2][c]){
						printf("MATCH: %c\n", c);
						sum += get_priority(c);
						found = true;
						break;
					}
				}
				for(int i = 0; i < 3; ++i){
					for(int j = 0; j < 128; ++j){
						occ[i][j] = false;
					}
				}
			}
			//printf("temp: %d\n", temp);
			//sum += temp;
			memset(buff, '\0', 256);
		}
		memset(buff, '\0', 256);
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
