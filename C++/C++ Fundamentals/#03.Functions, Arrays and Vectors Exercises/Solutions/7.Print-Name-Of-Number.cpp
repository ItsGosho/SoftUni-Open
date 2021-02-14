#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>
#include <string>

std::string getName(int number);

std::string getOnesPrefix(int ones);

std::string getTensPrefix(int tens);

int getDigitAtPosition(int number, int position);

std::string getTenToNineteenNames(int number);

int concatInts(int a, int b);

int concatInts(int a, int b, int c);

int main() {

    //int input = 112621;
    int input = 112621;
    //std::cin >> input;

    std::map<int, std::string> test;

    std::string name = getName(input);

    std::cout << name;
    return 0;
}

std::string getName(int number) {

    if (number >= 0 && number <= 9) {
        return getOnesPrefix(number);
    } else if (number >= 10 && number <= 20) {
        return getTenToNineteenNames(number);
    } else if (number >= 21 && number <= 99) {
        int firstDigit = getDigitAtPosition(number, 0);
        int secondDigit = getDigitAtPosition(number, 1);

        return getTensPrefix(firstDigit) + (secondDigit != 0 ? " " + getOnesPrefix(secondDigit) : "");
    } else if (number >= 100 && number <= 999) {
        int firstDigit = getDigitAtPosition(number, 0);
        int secondDigit = getDigitAtPosition(number, 1);
        int thirdDigit = getDigitAtPosition(number, 2);

        std::string sequence = getName(firstDigit) += " hundred";

        if (secondDigit == 0 && thirdDigit == 0) {
            return sequence;
        } else if (secondDigit == 0) {
            return sequence + " " + getOnesPrefix(thirdDigit);
        } else if (thirdDigit == 0) {
            return sequence + " " + getTensPrefix(secondDigit);
        } else {
            return sequence + " " + getName(concatInts(secondDigit, thirdDigit));
        }
    } else if (number >= 1000 && number <= 999999) {
        int firstDigit = getDigitAtPosition(number, 0);
        int secondDigit = getDigitAtPosition(number, 1);
        int thirdDigit = getDigitAtPosition(number, 2);
        int fourthDigit = getDigitAtPosition(number, 3);
        int fifthigit = getDigitAtPosition(number, 4);
        int sixthDigit = getDigitAtPosition(number, 5);

        int firstThreeDigitsConcatenated = concatInts(firstDigit, secondDigit, thirdDigit);
        int lastThreeDigitsConcatenated = concatInts(fourthDigit, fifthigit, sixthDigit);
        std::string sequence = getName(firstThreeDigitsConcatenated) + " thousand " + getName(lastThreeDigitsConcatenated);

        return sequence;
    }

}

int concatInts(int a, int b) {
    std::string s1 = std::to_string(a);
    std::string s2 = std::to_string(b);

    return stoi(s1 + s2);
}

int concatInts(int a, int b, int c) {
    std::string s1 = std::to_string(a);
    std::string s2 = std::to_string(b);
    std::string s3 = std::to_string(c);

    return stoi(s1 + s2 + s3);
}

std::string getTenToNineteenNames(int number) {
    switch (number) {
        case 10:
            return "ten";
        case 11:
            return "eleven";
        case 12:
            return "twelve";
        case 13:
            return "thirteen";
        case 14:
            return "fourteen";
        case 15:
            return "fifteen";
        case 16:
            return "sixteen";
        case 17:
            return "seventeen";
        case 18:
            return "eighteen";
        case 19:
            return "nineteen";
        case 20:
            return "twenty";
    }
}

std::string getOnesPrefix(int ones) {
    switch (ones) {
        case 0:
            return "zero";
        case 1:
            return "one";
        case 2:
            return "two";
        case 3:
            return "three";
        case 4:
            return "four";
        case 5:
            return "five";
        case 6:
            return "six";
        case 7:
            return "seven";
        case 8:
            return "eight";
        case 9:
            return "nine";
    }
    return "{ONES NOT IN RANGE}";
}

std::string getTensPrefix(int tens) {
    switch (tens) {
        case 2:
            return "twenty";
        case 3:
            return "thirty";
        case 4:
            return "forty";
        case 5:
            return "fifty";
        case 6:
            return "sixty";
        case 7:
            return "seventy";
        case 8:
            return "eighty";
        case 9:
            return "ninety";
    }
    return "{TENS NOT IN RANGE}";
}

int getDigitAtPosition(int number, int position) {
    for (int i = std::to_string(number).length() - 1; i >= 0; i--) {

        if (i == position)
            return number % 10;

        number /= 10;
    }
}

