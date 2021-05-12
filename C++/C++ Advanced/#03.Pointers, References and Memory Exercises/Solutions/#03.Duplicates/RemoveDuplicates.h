#include <list>
#include "Company.h"
#include "algorithm"

bool containsCompanyByName(std::list<Company *> companies, std::string name) {
    for (const Company *company : companies) {
        if (company->getName() == name) {
            return true;
        }
    }
    return false;
}

void removeDuplicates(std::list<Company *> &companies) {

    std::list<Company *> result;

    auto iterator = companies.cbegin();
    while (iterator != companies.cend()) {

        Company *company = *iterator;

        if (!containsCompanyByName(result, company->getName())) {
            result.push_back(company);
        }

        ++iterator;
    }

    companies = result;
}

