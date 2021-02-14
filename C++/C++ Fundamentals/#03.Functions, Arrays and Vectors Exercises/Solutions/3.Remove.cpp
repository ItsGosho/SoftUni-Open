#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>

int main() {
    int size = 0;

    std::cin >> size;

    std::vector<int> numbers;

    for (int i = 0; i < size; ++i) {
        int n = 0;
        std::cin >> n;
        numbers.push_back(n);
    }

    int searchedNumber = 0;
    std::cin >> searchedNumber;

    for (int i = 0; i < numbers.size(); i++) {
        if (numbers.at(i) == searchedNumber) {
            numbers.erase(numbers.begin() + i);
            i--;
        }
    }

    for (const auto &number : numbers) {
        std::cout << number << " ";
    }

    return 0;
}

