#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

bool isAlphabetical(char character);

bool isCharacterAtIndexNonAlphabetical(string text, int index);

int main() {

    string line;
    getline(cin, line);

    for (int i = 0; i < line.length(); ++i) {
        char character = line[i];

        if (isCharacterAtIndexNonAlphabetical(line, i - 1) && isAlphabetical(character)) {
            line[i] = toupper(character);
        }

    }

    cout << line;
}

bool isCharacterAtIndexNonAlphabetical(string text, int index) {

    if (index < 0 || index > text.length() - 1)
        return true;

    return !isAlphabetical(text[index]);
}

bool isAlphabetical(char character) {
    int asciiCode = (int) character;

    return (asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122);
}