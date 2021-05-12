#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

stack<int> readLineOfIntegers() {
    string line = "";
    getline(cin, line);
    stringstream lineStream(line);

    stack<int> integers;

    string k = "";

    while (lineStream >> k) {
        integers.push(stoi(k));
    }

    return integers;
}

int main() {
    stack<int> aRailCarts = readLineOfIntegers();
    stack<int> bRailCarts = readLineOfIntegers();

    stack<int> mergedCarts;
    string seq = "";

    while (true) {

        if (aRailCarts.empty()) {
            while (!bRailCarts.empty()) {
                mergedCarts.push(bRailCarts.top());
                seq += "B";
                bRailCarts.pop();
            }
            break;
        }

        if (bRailCarts.empty()) {
            while (!aRailCarts.empty()) {
                mergedCarts.push(aRailCarts.top());
                seq += "A";
                aRailCarts.pop();
            }
            break;
        }

        int aRailCartNumber = aRailCarts.top();
        int bRailCartNumber = bRailCarts.top();

        if (aRailCartNumber < bRailCartNumber) {
            mergedCarts.push(aRailCartNumber);
            aRailCarts.pop();
            seq += "A";
        } else {
            mergedCarts.push(bRailCartNumber);
            bRailCarts.pop();
            seq += "B";
        }
    }

    cout << seq << endl;

    while (!mergedCarts.empty()) {
        cout << mergedCarts.top() << " ";
        mergedCarts.pop();
    }
}