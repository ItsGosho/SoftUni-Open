#include "string"
#include "Company.h"
#include "map"
#include "ProfitCalculator.h"

std::string getProfitReport(Company *from, Company *to, std::map<int, ProfitCalculator> profitCalculators) {

    std::string result;

    Company *currentCompany = from;

    while (true) {

        ProfitCalculator profitCalculator = profitCalculators.find(currentCompany->getId())->second;

        int profit = profitCalculator.calculateProfit(*currentCompany);

        result += currentCompany->getName() + " = " + std::to_string(profit) + "\n";

        if (currentCompany == to)
            break;

        currentCompany++;
    }

    return result;
}