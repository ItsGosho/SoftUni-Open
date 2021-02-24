#include <iostream>
#include <sstream>
#include <string>
#include <stack>

using namespace std;

int main() {
    string consoleLine;

    stack<string> words;

    while (true) {

        getline(cin, consoleLine);
        stringstream consoleLineStream(consoleLine);

        if(consoleLine == "end")
            break;

        string word;
        while (consoleLineStream >> word)
            words.push(word);
    }

    while (!words.empty()) {
        cout << words.top() << " ";
        words.pop();
    }
}