#include <iostream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;


int main() {

    string text;
    getline(cin, text);
    stringstream textStream(text);

    string str;
    int sum = 0;
    vector<string> strings;

    while (textStream >> str) {
        try {
            sum += std::stoi(str);
        } catch (std::invalid_argument) {
            strings.push_back(str);
        }
    }

    cout << sum << endl;

    for (const auto &item : strings) {
        cout << item << " ";
    }
}