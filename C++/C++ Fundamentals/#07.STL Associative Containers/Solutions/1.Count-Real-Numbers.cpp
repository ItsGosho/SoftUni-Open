#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;


int main() {

    string consoleInputLine = "";
    getline(cin, consoleInputLine);
    stringstream consoleInputLineStream(consoleInputLine);


    map<float, int> realNumberOccurrences;

    float number;
    while (consoleInputLineStream >> number) {
        realNumberOccurrences[number]++;
    }

    for (const auto &item : realNumberOccurrences) {
        cout << item.first << " -> " << item.second << endl;
    }
}