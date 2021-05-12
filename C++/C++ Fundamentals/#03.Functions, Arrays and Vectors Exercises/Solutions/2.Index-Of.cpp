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

    bool isFound = false;
    for (int i = 0; i < numbers.size(); i++) {
        if (numbers.at(i) == searchedNumber) {
            std::cout << i;
            isFound = true;
            break;
        }
    }

    if(!isFound)
        std::cout << "-1";

    return 0;
}

