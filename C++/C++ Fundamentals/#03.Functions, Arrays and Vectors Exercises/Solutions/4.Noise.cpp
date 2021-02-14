#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>
#include <string>

int main() {

    char input = 'A';
    std::string concatenatedNumber = "";
    while (input != '.') {
        std::cin >> input;

        if (isdigit(input)) {
            concatenatedNumber += input;
        }
    }

    std::cout << std::sqrt(std::stoi(concatenatedNumber));

    return 0;
}

