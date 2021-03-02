#include "Company.h"
#include "vector"

Company* find(std::vector<Company*> companies, int searchId) {

    for (const auto &company : companies) {

        if(company->getId() == searchId)
            return company;
    }
    
    return nullptr;
}
