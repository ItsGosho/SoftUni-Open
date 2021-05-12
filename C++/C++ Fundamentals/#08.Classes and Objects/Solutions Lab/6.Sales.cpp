#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

class Sale {

private:
    string town;
    string product;
    double price;
    int quantity;

public:

    Sale() {

    }

    Sale(string town, string product, double price, int quantity) : Sale() {
        this->town = town;
        this->product = product;
        this->price = price;
        this->quantity = quantity;
    }

    const string &getTown() const {
        return town;
    }

    const string &getProduct() const {
        return product;
    }

    double getPrice() const {
        return price;
    }

    int getQuantity() const {
        return quantity;
    }
};

int readInt() {
    int integer;
    cin >> integer;
    return integer;
}

double readDouble() {
    double db;
    cin >> db;
    return db;
}


string readString() {
    string str;
    cin >> str;
    return str;
}

int main() {
    int studentsCount = readInt();

    vector<Sale> sales;

    for (int i = 0; i < studentsCount; ++i) {
        string town = readString();
        string product = readString();
        double price = readDouble();
        int quantity = readInt();

        sales.push_back(Sale(town, product, price, quantity));
    }

    map<string, double> totalSalesByTown;

    for (const auto &sale : sales) {
        totalSalesByTown[sale.getTown()] = sale.getPrice() * sale.getQuantity();
    }

    for (const auto &sale : totalSalesByTown) {
        cout << sale.first + " -> ";
        printf("%.2f\n", sale.second);
    }
}