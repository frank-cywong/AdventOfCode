#include "vector.h"
#include <stdio.h>

int main(){
	vector *v = init_vector();
	vadd(v, 11);
	vadd(v, -5);
	vprint(v);
	vremovelast(v);
	vadd(v, 7);
	vprint(v);
	vsort(v);
	vprint(v);
	v = vfree(v);
}
