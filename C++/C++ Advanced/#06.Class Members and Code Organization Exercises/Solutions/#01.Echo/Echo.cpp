#include "Echo.h"

bool echoOn = true;

void echo(const std::string& val) {
    using namespace std;

    if (echoOn)
        cout << val << endl;
}