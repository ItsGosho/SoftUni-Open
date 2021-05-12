#include <string>
#include <vector>

typedef std::vector<std::string> Vector;

Vector operator+(Vector first, Vector second) {

    Vector result;
    for (size_t i = 0; i < first.size(); ++i) {
        std::string str = first[i] + " " + second[i];
        result.push_back(str);
    }

    return result;
}