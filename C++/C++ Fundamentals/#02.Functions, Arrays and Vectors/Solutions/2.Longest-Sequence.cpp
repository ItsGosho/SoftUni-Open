#include <iostream>
#include <vector>


int main() {
    int arrLength = 0;
    std::cin >> arrLength;

    int bestStreak = 0;
    int bestNumber = 0;

    int currentStreak = 0;
    int currentNumber = 0;

    for (int i = 0; i < arrLength; i++) {
        int num;
        std::cin >> num;

        if (num == currentNumber) {
            currentStreak++;
            if(i + 1 == arrLength) {
                bestStreak = currentStreak;
                bestNumber = currentNumber;
            }

        } else {

            if (currentStreak >= bestStreak) {
                bestStreak = currentStreak;
                bestNumber = currentNumber;
            }

            if(i + 1 == arrLength && bestStreak <= 1) {
                bestStreak = 1;
                bestNumber = num;
            }

            currentStreak = 1;
            currentNumber = num;
        }
    }

    for (int i = 0; i < bestStreak; ++i) {
        std::cout << bestNumber << " ";
    }

    return 0;
}

