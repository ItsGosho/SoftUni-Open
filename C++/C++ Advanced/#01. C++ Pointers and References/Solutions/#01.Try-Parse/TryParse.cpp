#include <string>
#include <stdexcept>

bool tryParse(std::string a, int& b) {

    try {
        b = std::stoi(a);
        return true;
    } catch (std::invalid_argument ex) {
        return false;
    }
}
