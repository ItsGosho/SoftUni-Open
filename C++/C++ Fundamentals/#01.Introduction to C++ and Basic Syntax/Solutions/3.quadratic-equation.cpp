#include <iostream>
#include <string>
#include <math.h>

int main()
{
    std::string aConsoleInput = "";
    std::string bConsoleInput = "";
    std::string cConsoleInput = "";

    std::cin >> aConsoleInput;
    std::cin >> bConsoleInput;
    std::cin >> cConsoleInput;

    float a = std::stof(aConsoleInput);
    float b = std::stof(bConsoleInput);
    float c = std::stof(cConsoleInput);

    float discriminant = std::pow(b, 2) - 4 * a * c;

    if (discriminant < 0)
    {
        std::cout << "no roots";
        return 0;
    }

    float x1 = (-b + sqrt(discriminant)) / (2 * a);
    float x2 = (-b - sqrt(discriminant)) / (2 * a);

    if (x1 != x2)
        std::cout << x1 << " " << x2;
    else
        std::cout << x1;
}