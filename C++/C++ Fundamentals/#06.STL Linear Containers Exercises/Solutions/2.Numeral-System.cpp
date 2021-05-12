#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>

using namespace std;

string consoleReadLine() {
    string temp;
    getline(cin, temp);
    return temp;
}

vector<char> convertToCharVector(string str) {
    vector<char> result;

    result.reserve(str.size());
    for (const auto &item : str) {
        result.push_back(item);
    }

    return result;
}

int getCharacterIndex(vector<char> vec, char character) {

    for (size_t i = 0; i < vec.size(); ++i) {
        if (vec[i] == character)
            return i;
    }

    return -1;
}

int convertFromCustomToBase10(string encoded, vector<char> numeralSystem) {
    string createdNumber;
    for (int i = 0; i < encoded.size(); ++i) {
        char character = encoded[i];

        int characterNumeral = getCharacterIndex(numeralSystem, character);

        if (characterNumeral == -1)
            continue;

        createdNumber += to_string(characterNumeral);
    }

    return stoi(createdNumber);
}

string convertFromBase10ToCustom(int number, vector<char> numeralSystem) {
    string numberAsString = to_string(number);


    string result;
    for (const char &item : numberAsString) {
        int index = item - '0';
        char test = numeralSystem[index];

        result += test;
    }

    return result;
}

int main() {
    string numeralSystemInput = consoleReadLine();
    string firstNumbersInput = consoleReadLine();
    string secondNumbersInput = consoleReadLine();

    vector<char> numeralSystem = convertToCharVector(numeralSystemInput);

    int firstNumber = convertFromCustomToBase10(firstNumbersInput, numeralSystem);
    int secondNumber = convertFromCustomToBase10(secondNumbersInput, numeralSystem);
    int finalNumber = firstNumber + secondNumber;

    cout << convertFromBase10ToCustom(finalNumber, numeralSystem);

}