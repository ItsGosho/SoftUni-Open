#include "Company.h"
#include "string"
#include "map"
#include "vector"

using namespace std;


vector<string> split(string s, string delimiter) {
    size_t pos_start = 0, pos_end, delim_len = delimiter.length();
    string token;
    vector<string> res;

    while ((pos_end = s.find(delimiter, pos_start)) != string::npos) {
        token = s.substr(pos_start, pos_end - pos_start);
        pos_start = pos_end + delim_len;
        res.push_back(token);
    }

    string k = s.substr(pos_start);
    if (k != "")
        res.push_back(k);
    return res;
}


Company *
parseUniqueCompanies(const string input, int &companiesNumber, string (*uniqueIdentifierGenerator)(const Company &)) {

    vector<string> splittedInputs = split(input, "\n");

    vector<Company> companies;
    map<string, Company> dubChecker;

    for (const string &splittedInput : splittedInputs) {
        vector<string> splittedData = split(splittedInput, " ");
        int companyId = stoi(splittedData[0]);
        string companyName = splittedData[1];

        Company company(companyId, companyName);
        string idNew = uniqueIdentifierGenerator(company);


        //if dubChecker already has that id dont push

        if (dubChecker.find(idNew) == dubChecker.end()) {
            dubChecker.insert(pair<string, Company>(idNew, company));
            companies.push_back(company);
        }
    }

    companiesNumber = companies.size();
    auto salamche = new Company[(int) companies.size()];
    copy(companies.begin(), companies.end(), salamche);
    return salamche;
}