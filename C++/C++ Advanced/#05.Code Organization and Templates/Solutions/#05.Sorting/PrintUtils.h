#ifndef _05_SORTING_PRINTUTILS_H
#define _05_SORTING_PRINTUTILS_H

#include <iostream>
#include <sstream>
#include <vector>
#include <set>

template<typename T>
void printContainer(typename T::iterator begin, typename T::iterator end) {
    while (begin != end) {
        std::cout << *begin << " ";
        ++begin;
    }
    std::cout << std::endl;
}

#endif //_05_SORTING_PRINTUTILS_H
