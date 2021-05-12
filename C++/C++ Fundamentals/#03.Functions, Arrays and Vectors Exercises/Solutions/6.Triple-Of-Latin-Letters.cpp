#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <limits>
#include <string>

int main() {

    int input = 0;
    std::cin >> input;

    int max = 96 + input;
    for (int i = 97; i <= max; i++) {
        for (int j = 97; j <= max; j++) {
            for (int k = 97; k <= max ; k++) {
                std::cout << (char) i << (char) j << (char) k << "\n";
            }
        }
    }


    return 0;
}