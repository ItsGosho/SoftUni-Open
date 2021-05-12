#include "Company.h"
#include "vector"

typedef unsigned char byte;

Company readCompanyFromMemory(byte *&bytePointer) {

    int id = *bytePointer;
    bytePointer++;

    std::string name;

    while (*bytePointer != '\0') {
        name += *bytePointer;
        bytePointer++;
    }

    int totalEmployees = *(++bytePointer);

    std::vector<std::pair<char, char> > employees;
    for (int i = 0; i < totalEmployees; ++i) {
        char firstInitial = *(++bytePointer);
        char secondInitial = *(++bytePointer);

        employees.push_back(std::pair<char, char>(firstInitial, secondInitial));
    }

    bytePointer++;
    return Company(id, name, employees);
}

std::vector<Company> readCompaniesFromMemory(byte *firstBytePointer, int companiesNumber) {

    byte *bytePointer = firstBytePointer;
    std::vector<Company> companies{};

    for (int i = 0; i < companiesNumber; ++i) {
        Company company = readCompanyFromMemory(bytePointer);
        companies.push_back(company);
    }

    return companies;
}