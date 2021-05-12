#include <iostream>
#include <string>

using namespace std;

int main() {
    string consoleLine;
    getline(cin, consoleLine);

    bool invalid = false;

    int i = 0;
    while (true) {
        i++;

        if (i == consoleLine.size() || invalid)
            break;

        char bracket = consoleLine[i];
        char nextBracket = consoleLine[i + 1];

        if (bracket == '(')
            switch (nextBracket) {

                case ')':
                    consoleLine.erase(consoleLine.begin() + i, consoleLine.begin() + i + 2);
                    i = 0;
                    break;
                case '}':
                case ']':
                case '{':
                case '[':
                    invalid = true;
                    break;
            }
        else if (bracket == '{')
            switch (nextBracket) {

                case '}':
                    consoleLine.erase(consoleLine.begin() + i, consoleLine.begin() + i + 2);
                    i = 0;
                    break;

                case ')':
                case ']':
                    invalid = true;
                    break;
            }
        else if (bracket == '[')
            switch (nextBracket) {

                case ']':
                    consoleLine.erase(consoleLine.begin() + i, consoleLine.begin() + i + 2);
                    i = 0;
                    break;

                case ')':
                case '}':
                case '{':
                    invalid = true;
                    break;
            }
    }

    cout << (invalid ? "invalid" : "valid");
}