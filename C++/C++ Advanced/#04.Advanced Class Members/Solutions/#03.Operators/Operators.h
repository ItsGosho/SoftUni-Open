#include <ostream>
#include <vector>
#include <string>
#include <sstream>


typedef std::vector<std::string> Vector;

Vector& operator<<(Vector& vector, std::string string) {
    vector.push_back(string);
    return vector;
}

std::string operator+(std::string string, int integer) {
    return string + std::to_string(integer);
}

std::ostream& operator<<(std::ostream& ostream, Vector vector) {

    for (const std::string& item : vector) {
        ostream << item << std::endl;
    }

    return ostream;
}