#include <iostream>
#include <string>
#include <math.h>
#include <limits>

int main()
{
    std::string firstLineConsole = "";

    std::cin >> firstLineConsole;

    int N = std::stoi(firstLineConsole);

    int min = std::numeric_limits<int>::max();
    int max = std::numeric_limits<int>::min();
    for (int i = 0; i < N; i++)
    {
        std::string read = "";
        std::cin >> read;

        int num = std::stoi(read);

        if (num < min)
            min = num;

         if (num > max)
            max = num;
    }

    std::cout << min << " " << max;
}