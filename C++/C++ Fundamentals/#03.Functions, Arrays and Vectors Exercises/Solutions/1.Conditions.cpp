#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>

int main() {
    int firstNumber = 0;
    int secondNumber = 0;

    std::cin >> firstNumber;
    std::cin >> secondNumber;

    bool isSymbolValid = false;
    int result = 0;
    while (!isSymbolValid) {
        char symbol;
        std::cin >> symbol;

        switch (symbol) {
            case '+':
                result = firstNumber + secondNumber;
                isSymbolValid = true;
                break;
            case '-':
                result = firstNumber - secondNumber;
                isSymbolValid = true;
                break;
            case '*':
                result = firstNumber * secondNumber;
                isSymbolValid = true;
                break;
            case '/':
                result = firstNumber / secondNumber;
                isSymbolValid = true;
                break;
            default:
                std::cout << "try again\n";
                break;
        }
    }

    std::cout << result;

    return 0;
}

