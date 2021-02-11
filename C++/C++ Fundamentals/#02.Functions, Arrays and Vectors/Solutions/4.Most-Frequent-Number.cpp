#include <iostream>
#include <vector>
#include <map>


int main() {
    int arrLength = 0;
    std::cin >> arrLength;

    std::map<int, int> occurrences = {};

    int biggestOccurrence = 1;
    for (int i = 0; i < arrLength; i++) {
        int num;
        std::cin >> num;

        if (occurrences[num] == 0) {
            occurrences[num] = 1;
        } else {
            occurrences[num] = occurrences[num] + 1;

            if (occurrences[num] > biggestOccurrence)
                biggestOccurrence = occurrences[num];
        }
    }

    for (const auto &item : occurrences) {
        if (item.second == biggestOccurrence)
            std::cout << item.first << ' ';
    }

    return 0;
}

