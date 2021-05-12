//
// Created by Gosho on 16-Mar-21.
//

#ifndef _04_SPLIT_AND_JOIN_SPLIT_H
#define _04_SPLIT_AND_JOIN_SPLIT_H

#include <vector>
#include <string>

template<typename T>
std::vector<T> split(const std::string& line, const char& separator) {

    std::vector<T> separatedElements;

    std::string concatenatedElement;

    for (size_t i = 0; i <= line.length(); i++) {
        char character = line[i];

        if (character == separator || i == line.length()) {
            std::stringstream stringstream(concatenatedElement);
            T element;
            stringstream >> element;
            separatedElements.push_back(element);
            concatenatedElement = "";
        } else {
            concatenatedElement += character;
        }
    }

    return separatedElements;
}


#endif //_04_SPLIT_AND_JOIN_SPLIT_H
