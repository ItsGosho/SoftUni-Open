#include <iostream>
#include <vector>
#include <map>


int main() {
    int arrLength = 0;
    std::cin >> arrLength;

    std::vector<int> numbers;

    for (int i = 0; i < arrLength; i++) {
        int num;
        std::cin >> num;
        numbers.push_back(num);
    }

    for (int i = 0; i < numbers.size(); ++i) {
        int num = numbers[i];

        for (int j = 0; j < numbers.size(); ++j) {
            std::cout << num * numbers[j] << " ";
        }
    }

    return 0;
}

