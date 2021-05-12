#include "Company.h"
#include "algorithm"

void swapPointerValues(Company* a, Company* b) {
    Company firstTemp = *a;
    *a = *b;
    *b = firstTemp;
}

void sortBy(Company **begin, Company **end, bool (*sortPredicate)(const Company &, const Company &)) {

    Company **beginCopy = begin;
    Company **endCopy = end;

    if(begin == end) {
        return;
    }

    bool isSwapMade = false;
    while (true) {

        begin = beginCopy;
        endCopy = endCopy;

        while (begin + 1 != end) {
            Company *first = *begin;
            Company *second = *(begin + 1);

            bool swapRequired = !sortPredicate(*first, *second);

            if (swapRequired) {
                swapPointerValues(first, second);
                isSwapMade = true;
            }

            begin++;
        }

        if (!isSwapMade) {
            break;
        }

        isSwapMade = false;
    }
}