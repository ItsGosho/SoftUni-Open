#ifndef _04_SPLIT_AND_JOIN_JOIN_H
#define _04_SPLIT_AND_JOIN_JOIN_H

#include <vector>
#include <string>
#include <ostream>
#include <sstream>

template<typename T>
std::string join(const std::vector<T>& elements, const std::string& separator) {
    std::string result;

    std::stringstream stringstream;

    for (size_t i = 0; i < elements.size(); i++) {
        T element = elements[i];

        if (i + 1 == elements.size())
            stringstream << element;
        else
            stringstream << element << separator;
    }

    return stringstream.str();
}


#endif //_04_SPLIT_AND_JOIN_JOIN_H
