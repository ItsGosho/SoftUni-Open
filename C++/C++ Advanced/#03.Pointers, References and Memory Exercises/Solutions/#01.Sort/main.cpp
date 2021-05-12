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

std::vector<Company> readCompaniesFromConsole() {

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

    return companies;
}

std::string readSortParameterFromConsole() {
    std::string sortParameter;
    std::cin >> sortParameter;
    return sortParameter;
}

void sortBySortParameter(const std::string &sortParameter, std::vector<Company> &companies) {

    if (sortParameter == "id")
        std::sort(companies.begin(), companies.end(), sortById);
    else if (sortParameter == "name")
        std::sort(companies.begin(), companies.end(), sortByName);
}

int main() {

    std::vector<Company> companies = readCompaniesFromConsole();
    std::string sortParameter = readSortParameterFromConsole();

    sortBySortParameter(sortParameter, companies);

    for (Company company : companies) {
        std::cout << company.toString() << std::endl;
    }

    return 0;
}
