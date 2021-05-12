#include "Company.h"
#include "string"
#include <sstream>

using namespace std;

vector<Company> readCompanies(const string &input) {
    istringstream stringStream(input);
    vector<Company> companies;

    Company company;
    while (stringStream >> company) {
        companies.push_back(company);
    }

    return companies;
}

byte toByte(int a) {
    return static_cast<byte>(a);
}

vector<byte> getBytes(const vector<Company> &companies) {
    vector<byte> bytes;

    bytes.push_back(toByte(companies.size()));

    for (const Company &company : companies) {

        bytes.push_back(toByte(company.getId()));

        for (int i = 0; i < company.getName().size(); ++i) {
            bytes.push_back(toByte(company.getName()[i]));
        }

        bytes.push_back(0);
        bytes.push_back(toByte(company.getEmployees().size()));

        for (const pair<char, char> &employee : company.getEmployees()) {
            bytes.push_back(toByte(employee.first));
            bytes.push_back(toByte(employee.second));
        }
    }

    return bytes;
}

byte *serializeToMemory(const string &input, size_t &bytesWritten) {

    vector<Company> companies = readCompanies(input);
    vector<byte> bytes = getBytes(companies);

    bytesWritten = bytes.size();
    byte *result = new byte[bytes.size()];
    copy(bytes.begin(), bytes.end(), result);

    return result;
}