#include "PrintUtils.h"

template<typename T>
void printVector(const std::vector<T>& vector) {

    for (const auto& item : vector) {
        std::cout << item << " ";
    }

}
