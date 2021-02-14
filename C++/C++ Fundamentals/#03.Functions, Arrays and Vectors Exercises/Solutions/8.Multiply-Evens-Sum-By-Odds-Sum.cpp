#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>
#include <string>

std::vector<int> integerToArray(int x) {
    std::vector<int> resultArray;

    while (true) {
        resultArray.insert(resultArray.begin(), x % 10);
        x /= 10;
        if (x == 0)
            return resultArray;
    }
}

int main() {

    int number = 0;
    std::cin >> number;

    std::vector<int> test = integerToArray(number);

    int odd = 0;
    int even = 0;
    for (const auto &item : test) {
        if (item % 2 == 0)
            even += item;
        else
            odd += item;
    }

    std::cout << (even * odd);
}
