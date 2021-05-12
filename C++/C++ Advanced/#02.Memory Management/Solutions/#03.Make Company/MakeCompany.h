#include "string"
#include "vector"
#include "Company.h"

using namespace std;

vector<Company *> toDelete;

void removeOld();

using test = const vector<string>&;

Company *makeCompany(test properties) {
    removeOld();

    int id = stoi(properties[0]);
    string name = properties[1];

    vector<pair<char, char>> employees;

    for (int i = 2; i < properties.size(); ++i) {
        string property = properties[i];
        employees.push_back(pair<char, char>(property[0], property[1]));
    }

    Company *company = new Company(id, name, employees);
    toDelete.push_back(company);

    return company;
}

void removeOld() {

    auto iterator = toDelete.cbegin();
    while (iterator != toDelete.cend()) {

        Company *company = *iterator;

        iterator = toDelete.erase(iterator);
        delete company;
    }

}