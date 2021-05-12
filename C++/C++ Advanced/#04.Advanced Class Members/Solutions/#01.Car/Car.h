#include <string>

using namespace std;

class Car {

private:
    std::string brand;
    std::string model;
    int year;

public:
    Car(const std::string& brand, const std::string& model, const int& year) : brand(brand), model(model), year(year) {}

    std::string GetBrand() const {
        return this->brand;
    }

    std::string GetModel() const {
        return this->model;
    }

    int GetYear() const {
        return this->year;
    }
};