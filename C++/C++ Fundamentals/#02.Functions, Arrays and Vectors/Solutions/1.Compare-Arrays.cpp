#include <iostream>
#include <vector>

bool areArraysEqual(std::vector<int> first, std::vector<int> second);

int main()
{
    int firstArrLength = 0;
    std::cin >> firstArrLength;

    std::vector<int> firstArr;

    for (int i = 0; i < firstArrLength; i++)
    {
        int num;
        std::cin >> num;
        firstArr.push_back(num);
    }

    int secondArrLength = 0;
    std::cin >> secondArrLength;

    std::vector<int> secondArr;

    for (int i = 0; i < secondArrLength; i++)
    {
        int num;
        std::cin >> num;
        secondArr.push_back(num);
    }

    bool areEqual = areArraysEqual(firstArr, secondArr);

    std::cout << (areEqual ? "equal" : "not equal");

    return 0;
}

bool areArraysEqual(const std::vector<int> first,const std::vector<int> second) {

    if(first.size() != second.size())
        return false;

    for (int i = 0; i < first.size(); ++i) {
        int firstElement = first[i];
        int secondElement = second[i];

        if(firstElement != secondElement)
            return false;
    }

    return true;
}