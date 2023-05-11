#include <CL/cl.hpp>

#include <iostream>
#include <algorithm>
#include <random>
#include <fstream>
#include <chrono>
#include <ratio>
#include <numeric>
#include <iomanip>
#include <functional>

using namespace std;
using namespace std::chrono;

void mul(const float* A, const float* B, float* C, int N) {
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

class GPGPU {

	cl::Context context;
	cl::CommandQueue queue;
	cl::Kernel kernel;

public:

	GPGPU() {
		// pobierz urz¹dzenia i platformy
		std::vector<cl::Platform> platforms;
		std::vector<cl::Device> devices;
		cl::Platform::get(&platforms);

		cout << "LIST OF ALL GPU DEVICES\n";

		for (int i = 0; i < platforms.size(); i++)
		{
			platforms[i].getDevices(CL_DEVICE_TYPE_GPU, &devices);
			for (int j = 0; j < devices.size(); j++) {
				cout << platforms[i].getInfo<CL_PLATFORM_NAME>() << ": " << devices[j].getInfo<CL_DEVICE_NAME>() << endl;
			}
		}
		platforms[0].getDevices(CL_DEVICE_TYPE_GPU, &devices);

		cout << "--------------\n";

		// wyœwietl informacje o platformie i urz¹dzeniu
		cout << platforms[0].getInfo<CL_PLATFORM_NAME>() << ": " << devices[0].getInfo<CL_DEVICE_NAME>() << endl;

		// utwórz kontekst i kolejkê
		context = cl::Context(devices);
		queue = cl::CommandQueue(context, devices[0]);

		// odczytaj kod kernela
		string code, line;
		std::ifstream file("kernels.cl");
		while (std::getline(file, line)) {
			code += line + "\n";
		}

		cl::Program::Sources src;
		src.push_back(make_pair(code.c_str(), code.size() + 1));

		// skompiluj program
		int cl_err;
		cl::Program program(context, src, &cl_err);

		cout << "Compiling kernel...";
		cl_err = program.build();
		string buildLog = program.getBuildInfo<CL_PROGRAM_BUILD_LOG>(devices[0]);

		if (cl_err != 0) {
			cout << "ERROR" << endl << buildLog << endl;
			throw runtime_error("Kernel compilation error");
		}
		else {
			cout << "OK" << endl;
		}

		kernel = cl::Kernel(program, "mul", &cl_err);
	}

	void mul(
		const float* A,
		const float* B,
		float* C,
		int N) {

		// utworz bufory
		cl::Buffer bufferA(context, CL_MEM_READ_ONLY, N * N * sizeof(float));
		cl::Buffer bufferB(context, CL_MEM_READ_ONLY, N * N * sizeof(float));
		cl::Buffer bufferC(context, CL_MEM_WRITE_ONLY, N * N * sizeof(float));

		queue.enqueueWriteBuffer(bufferA, true, 0, N * N * sizeof(float), A);
		queue.enqueueWriteBuffer(bufferB, true, 0, N * N * sizeof(float), B);

		kernel.setArg(0, bufferA);
		kernel.setArg(1, bufferB);
		kernel.setArg(2, bufferC);
		kernel.setArg(3, N);

		auto ceil_div = [](int a, int b) { return (a + b - 1) / b;  }; // sufit z a/b

		// konfiguruj zakresy
		cl::NDRange localRange(32, 32); // za³o¿enie
		cl::NDRange globalRange(32 * ceil_div(N, 32), 32 * ceil_div(N, 32));
		cl::NDRange offsetRange(0, 0);

		// wywo³aj kernel
		queue.enqueueNDRangeKernel(kernel, offsetRange, globalRange, localRange);
		queue.enqueueReadBuffer(bufferC, true, 0, N * N * sizeof(float), C);
	}
};

int main() {

	// generacja danych
	std::uniform_real_distribution<float> dist(0, 1);
	std::mt19937 engine;

	int W = 1001;
	int H = 1001;
	std::vector<float> A(W * H);
	std::vector<float> B(W * H);

	std::vector<float> C(W * H);
	std::vector<float> Cgpu(W * H);

	std::generate(A.begin(), A.end(), [&dist, &engine]() { return dist(engine); });
	std::generate(B.begin(), B.end(), [&dist, &engine]() { return dist(engine); });

	GPGPU gpgpu;

	high_resolution_clock::time_point t1, t2;

	std::vector<float> macA = { 2,1,3, -1,4,0, 0, 0,0  };
	std::vector<float> macB = { 1,3,2, -2,0,1, 5,-3,2 };
	std::vector<float> macC(9);

	t1 = high_resolution_clock::now();
	mul(macA.data(), macB.data(), macC.data(), 3);
	t2 = high_resolution_clock::now();
	cout << "CPU time: " << duration_cast<duration<double>>(t2 - t1).count() << endl;

	for (int i = 0; i < 9; i++) {
		cout << macC[i] << " ";
		if (i % 3 == 2) cout << endl;
	}

	t1 = high_resolution_clock::now();
	gpgpu.mul(macA.data(), macB.data(), macC.data(), 3);
	t2 = high_resolution_clock::now();
	cout << "GPU time: " << duration_cast<duration<double>>(t2 - t1).count() << endl;

	for (int i = 0; i < 9; i++) {
		cout << macC[i] << " ";
		if (i % 3 == 2) cout << endl;
	}

	return 0;
}