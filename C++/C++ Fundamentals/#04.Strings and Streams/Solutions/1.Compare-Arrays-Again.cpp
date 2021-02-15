#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

vector<int> readNumbersLine();

int main() {

    vector<int> firstLineNumbers = readNumbersLine();
    vector<int> secondLineNumbers = readNumbersLine();

    if(firstLineNumbers.size() != secondLineNumbers.size()) {
        cout << "not equal";
        return -1;
    }

    for (int i = 0; i < firstLineNumbers.size(); ++i) {
        int firstNumber = firstLineNumbers[i];
        int secondNumber = secondLineNumbers[i];

        if(firstNumber != secondNumber) {
            cout << "not equal";
            return -1;
        }
    }

    cout << "equal";
}

vector<int> readNumbersLine() {
    string line;
    getline(std::cin, line);
    stringstream lineStream(line);

    vector<int> numbers;
    int currentNumber = 0;
    while (lineStream >> currentNumber) {
        numbers.push_back(currentNumber);
    }

    return numbers;
}