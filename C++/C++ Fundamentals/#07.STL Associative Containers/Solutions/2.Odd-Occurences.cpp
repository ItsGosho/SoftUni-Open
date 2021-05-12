#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

string join(vector<string> elements, string delimiter) {
    string result;

    if (elements.size() == 1)
        return elements[0];

    for (vector<string>::iterator i = elements.begin(); i != elements.end(); i++) {

        if (i == elements.end() - 1) {
            result += *i;
            break;
        }

        result += *i + delimiter;
    }

    return result;
}


int main() {

    string consoleInputLine = "";
    getline(cin, consoleInputLine);
    stringstream consoleInputLineStream(consoleInputLine);


    unordered_map<string, int> wordOccurrences;

    string word;
    while (consoleInputLineStream >> word) {
        transform(word.begin(), word.end(), word.begin(), tolower);
        wordOccurrences[word]++;
    }

    vector<string> keys;

    for (const auto &item : wordOccurrences) {
        if (item.second % 2 != 0)
            keys.push_back(item.first);
    }

    cout << join(keys, ", ");
}