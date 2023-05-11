kernel void calculate(
	global const float *A, 
	global const float *B, 
	global float *C,
	int W, int H,
	int complexity) 
{
	int x = get_global_id(0);
	int y = get_global_id(1);
	
	if (x < W && y < H) {
		int id = y * W + x;
		
		float a = A[id];
		float b = B[id];
		float c;
		
		if (complexity == 0) {
			// few to do
			c = a + b;
		}
		else if (complexity == 1) {
			// more to do
			c = sqrt(a * sin(b) + b * log(a+1));	
		}
		else {
			// lots to do
			c = 0; //
			for (int i = 0; i < 1000; ++i) {
				c += sqrt(a * sin(c) + b * log(c + 1));
				//	c += native_sqrt(a * native_sin(c) + b * native_log(c+1));
			}
		}
			
		C[id] = c;	
	}	
}

kernel void mul(global const float* A, global const float* B, global float* C, const int N) {
	int matSize = N * N;
	int column = 0;
	int row = 0;

	for (int indexC = 0; indexC < matSize; indexC++)
	{
		column = indexC % N;
		row = indexC / N;

		for (int i = 0; i < N; i++) {
			C[indexC] += A[row * N + i] * B[column + i * N];
		}
	}
}