#include <iostream>
#include <string>


int main()
{
	std::string firstInput = "";
	std::string secondInput = "";

	std::cin >> firstInput;
	std::cin >> secondInput;

	int firstNumber = atoi(firstInput.c_str());
	int secondNumber = atoi(secondInput.c_str());

	if (firstNumber < secondNumber) {
		std::cout << firstNumber << " " << secondNumber;
	}
	else if (firstNumber > secondNumber) {
		std::cout << secondNumber << " " << firstNumber;
	}
	else {
		std::cout << secondNumber << " " << firstNumber;
	}
}