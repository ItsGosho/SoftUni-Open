#include <iostream>
#include <vector>


int main() {
    int arrLength = 0;
    std::cin >> arrLength;

    std::vector<int> nums;

    int sum = 0;

    for (int i = 0; i < arrLength; i++) {
        int num;
        std::cin >> num;
        sum += num;

        nums.push_back(num);
    }

    int avg = sum / arrLength;

    for (const auto &item : nums) {
        if(item >= avg)
            std::cout << item << " ";
    }

    return 0;
}

