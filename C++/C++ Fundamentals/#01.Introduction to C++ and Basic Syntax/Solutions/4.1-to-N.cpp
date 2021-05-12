#include <iostream>
#include <string>
#include <math.h>

int main()
{
    std::string consoleInput = "";

    std::cin >> consoleInput;

    int N = std::stoi(consoleInput);

    for (int i = 1; i <= N; i++)
    {
        std::cout << i << " ";
    }
}