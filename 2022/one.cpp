#include <cstdio>
#include <cstring>
#include <set>

using namespace std;

int main(){
	FILE *fs = fopen("1.input", "r");
	int sum = 0;
	int temp;
	char buff[256];
	set<int> nums;
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			sscanf(buff, "%d\n", &temp);
			//printf("temp: %d\n", temp);
			sum += temp;
		} else {
			//printf("test\n");
			if(sum != 0){
				nums.insert(sum);
				sum = 0;
			}
		}
	}
/*
	for (auto rit=nums.rbegin(); rit != nums.rend(); ++rit){
		printf("%d ", *rit);
	}
*/
	int s2 = 0;
	auto rit = nums.rbegin();
	for(int i = 0; i < 3; ++i){
		s2 += *rit;
		++rit;
	}
	printf("%d\n", s2);
	return 0;
}
