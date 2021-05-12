#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

bool isDigit(char character);

int main() {

    string text;
    getline(cin, text);
    stringstream textStream(text);

    string str;

    int maxFound = 0;
    while (textStream >> str) {

        string concatenatedNumber = "";

        for (int i = 0; i < str.size(); ++i) {
            char character = str[i];

            if (isDigit(character)) {
                concatenatedNumber += character;
            }
        }

        if(stoi(concatenatedNumber) > maxFound)
            maxFound = stoi(concatenatedNumber);
    }

    cout << maxFound;
}

bool isDigit(char character) {
    int asciiCode = (int) character;

    return asciiCode >= 48 && asciiCode <= 57;
}