#include "list"
#include "Company.h"

void removeInvalid(std::list<Company *> &companies) {

    auto iterator = companies.cbegin();
    while (iterator != companies.cend()) {

        Company *company = *iterator;

        if (company->getId() < 0) {
            iterator = companies.erase(iterator);
            delete company;
        } else {
            ++iterator;
        }
    }

}