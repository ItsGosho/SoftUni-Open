#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

class Sentence {

private:
    string sentence;
    vector<string> sentenceSplitted;

    void splitSentence() {
        string word;
        for (int i = 0; i < sentence.size(); ++i) {
            if (sentence[i] != ' ') {
                word += sentence[i];
            } else {
                this->sentenceSplitted.push_back(word);
                word = "";
            }
        }

        if (word != "")
            this->sentenceSplitted.push_back(word);
    }

public:

    Sentence() {

    }

    Sentence(string sentence) : Sentence() {
        this->sentence = sentence;
        this->splitSentence();
    }

    vector<string> getShiftedSentence(int shifts) {
        int actualShifts = shifts % this->sentenceSplitted.size();

        map<int, int> positions;

        int indexCounter = 0;
        for (size_t i = 0; i < this->sentenceSplitted.size(); ++i) {
            int nextIndex = i + actualShifts;

            if (nextIndex > this->sentenceSplitted.size() - 1) {
                nextIndex = indexCounter;
                indexCounter++;
            }

            positions.insert(pair<int, int>(nextIndex, i));
        }

        vector<string> result;

        for (const auto &position : positions) {
            result.push_back(this->sentenceSplitted[position.second]);
        }

        return result;
    }
};

string readLine() {
    string str;
    getline(cin, str);
    return str;
}

int readInt() {
    int integer;
    cin >> integer;
    return integer;
}

int main() {
    Sentence sentence(readLine());
    int shifts = readInt();

    vector<string> result = sentence.getShiftedSentence(shifts);

    for (const auto &item : result) {
        cout << item << endl;
    }
}