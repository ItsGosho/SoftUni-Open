#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>

int main() {
    int arrLength = 0;
    std::cin >> arrLength;

    std::vector<int> numbers;

    for (int i = 0; i < arrLength; i++) {
        int num;
        std::cin >> num;
        numbers.push_back(num);
    }

    int currentClosesDiff = std::numeric_limits<int>::max();

    for (int i = 0; i < numbers.size(); ++i) {
        int num = numbers[i];

        for (int j = i + 1; j < numbers.size(); ++j) {

            int abs = std::abs(num - numbers[j]);

            if (abs < currentClosesDiff)
                currentClosesDiff = abs;
        }
    }

    std::cout << (currentClosesDiff == std::numeric_limits<int>::max() ? 0 : currentClosesDiff);

    return 0;
}

