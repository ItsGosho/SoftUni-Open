#include "string"
#include "vector"
#include "algorithm"

std::string minBy(std::vector<std::string> values, bool (*comparator)(const std::string &, const std::string &)) {

    std::sort(values.begin(), values.end(), comparator);

    return values[0];
}