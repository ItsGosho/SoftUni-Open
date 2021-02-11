#include <iostream>
#include <string>
#include <math.h>
#include <limits>

int main()
{
    std::string firstLineConsole = "";
    std::string secondLineConsole = "";

    std::cin >> firstLineConsole;
    std::cin >> secondLineConsole;

    int a = std::stoi(firstLineConsole);
    int b = std::stoi(secondLineConsole);

    int R;
    while ((a % b) > 0)
    {
        R = a % b;
        a = b;
        b = R;
    }

    std::cout << b;
}