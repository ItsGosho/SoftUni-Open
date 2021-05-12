#include <sstream>

template<typename T>
bool Parser<T>::readNext(T& element) {

    std::string line;
    std::getline(this->in, line);
    std::istringstream iss(line);

    if(line == this->stopLine)
        return false;

    iss >> element;

    return true;
}