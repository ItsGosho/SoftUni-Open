#include <iostream>
#include <string>
#include <sstream>
#include "Company.h"
#include <algorithm>

bool sortByName(const Company &c1, const Company &c2) {
    return c1.getName() < c2.getName();
}

bool sortById(const Company &c1, const Company &c2) {
    return c1.getId() < c2.getId();
}



int main() {

    std::vector<Company> companies;
    std::string line;
    while (std::getline(std::cin, line) && line != "end") {
        std::istringstream lineStream(line);

        std::string companyName;
        int companyId;

        lineStream >> companyName;
        lineStream >> companyId;

        Company company(companyId, companyName);
        companies.push_back(company);
    }

    std::string sortParameter;
    std::cin >> sortParameter;

    if (sortParameter == "id")
        std::sort(companies.begin(), companies.end(), sortById);
    else if (sortParameter == "name")
        std::sort(companies.begin(), companies.end(), sortByName);

    for (Company company : companies) {
        std::cout << company.toString() << std::endl;
    }

    return 0;
}
