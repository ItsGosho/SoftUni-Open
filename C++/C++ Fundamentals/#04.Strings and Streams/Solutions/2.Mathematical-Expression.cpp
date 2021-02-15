#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

vector<char> readCharactersLine();

int main() {

    string line;
    cin >> line;

    int openingBrackets = 0;
    int closingBrackets = 0;

    for (int i = 0; i < line.length(); ++i) {

        if (line[i] == '(')
            openingBrackets++;
        else if (line[i] == ')')
            closingBrackets++;

    }

    if (openingBrackets != closingBrackets)
        cout << "incorrect";
    else
        cout << "correct";

}