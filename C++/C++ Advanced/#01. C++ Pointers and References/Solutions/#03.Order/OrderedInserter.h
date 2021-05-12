#include "Company.h"
#include "vector"
#include "algorithm"

class OrderedInserter {

private:
    std::vector<const Company *> &companies;

public:

    OrderedInserter(std::vector<const Company *> &companies) : companies(companies) {
        this->companies = std::vector<const Company *>();
    }


    void insert(const Company *company) {
        this->companies.push_back(company);

        std::sort(this->companies.begin(), this->companies.end(), OrderedInserter::sortFunction);
    }

    static bool sortFunction(const Company *first,const Company *second) {
        return first->getId() < second->getId();
    }
};