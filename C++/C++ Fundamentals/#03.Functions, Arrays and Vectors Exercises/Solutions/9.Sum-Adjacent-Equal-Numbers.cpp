#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>
#include <string>

int main() {

    int totalNumbers = 0;
    std::cin >> totalNumbers;

    std::vector<int> numbers;
    for (int i = 0; i < totalNumbers; ++i) {
        int read = 0;
        std::cin >> read;
        numbers.push_back(read);
    }

    bool isSumMade = true;
    while (isSumMade) {
        isSumMade = false;

        for (int i = 0; i < numbers.size() - 1; ++i) {
            int first = numbers.at(i);
            int second = numbers.at(i + 1);

            if(first == second) {
                isSumMade = true;
                int newNumber = first + second;
                numbers.erase(numbers.begin() + i + 1);
                numbers.at(i) = newNumber;
                break;
            }
        }
    }

    for (const auto &number : numbers) {
        std::cout << number << " ";
    }
}
