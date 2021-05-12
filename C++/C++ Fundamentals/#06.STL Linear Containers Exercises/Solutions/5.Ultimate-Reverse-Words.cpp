#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> punctuationCharacters = {".", ",", "\"", "'", "-", "?", ":", "!", ";"};

string consoleReadLine() {
    string temp;
    getline(cin, temp);
    return temp;
}

bool isPunctuation(string str) {
    return find(punctuationCharacters.begin(), punctuationCharacters.end(), str) != punctuationCharacters.end();
}

bool isPunctuation(char character) {
    string str(1, character);
    return find(punctuationCharacters.begin(), punctuationCharacters.end(), str) != punctuationCharacters.end();
}

vector<string> divideSentence(string sentence) {
    vector<string> words;
    string word;

    for (const char &item : sentence) {

        if (item == ' ' && !word.empty()) {
            words.push_back(word);
            word = "";
        } else if (item != ' ' && !isPunctuation(item)) {
            word += item;
        } else if (isPunctuation(item)) {
            words.push_back(word);
            word = "";
            words.push_back(string(1, item));
        }
    }

    return words;
}

vector<string> swap(vector<string> source, int index1, int index2) {
    string tmp = source[index1];
    source[index1] = source[index2];
    source[index2] = tmp;
    return source;
}

/*
 * To refactor it to my better and optimized way with classes later on
 */
int main() {
    string sentence = "Dude, what is this nightmare of a task!";

    vector<string> divided = divideSentence(sentence);

    int longest = 0;
    for (int i = 0; i < divided.size(); ++i) {
        if (divided[i].size() > longest)
            longest = divided[i].size();
    }

    int nextEndIndex = divided.size() - 1;
    for (int i = 0; i < divided.size(); ++i) {
        string wordFirst = divided[i];

        if (isPunctuation(wordFirst))
            continue;

        if (i >= nextEndIndex) {
            longest--;
        }

        for (int j = nextEndIndex; j >= 0; j--) {
            string wordSecond = divided[j];

            if (isPunctuation(wordSecond))
                continue;

            if (wordFirst.length() == wordSecond.length()) {
                divided = swap(divided, i, j);
                nextEndIndex = j - 1;
                break;
            }
        }
    }

    cout << isPunctuation('.');
}