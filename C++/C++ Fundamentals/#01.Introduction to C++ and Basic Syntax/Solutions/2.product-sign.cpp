#include <iostream>
#include <string>

int main()
{
    std::string consoleInput = "";

    std::getline(std::cin, consoleInput);

    int totalMinusSigns = 0;
    int totalPlusSigns = 0;

    for (char c : consoleInput)
    {
        if (c == '-')
            totalMinusSigns++;
    }

    std::string resultSign = totalMinusSigns % 2 == 0 ? "+" : "-";
    std::cout << resultSign;
}