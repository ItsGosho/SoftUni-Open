#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

class Student {

private:
    string firstName;
    string lastName;
    float averageGrade;

public:

    Student() {

    }

    Student(string firstName, string lastName, float averageGrade) : Student() {
        this->firstName = firstName;
        this->lastName = lastName;
        this->averageGrade = averageGrade;
    }

    string getFirstName() {
        return this->firstName;
    }

    string getLastName() {
        return this->lastName;
    }

    float getAverageGrade() {
        return this->averageGrade;
    }
};

string readString() {
    string str;
    cin >> str;
    return str;
}

float readFloat() {
    float floatt;
    cin >> floatt;
    return floatt;
}

int readInt() {
    int integer;
    cin >> integer;
    return integer;
}

int getAverage(vector<Student> students) {
    float sum = 0;

    for (Student student : students) {
        sum += student.getAverageGrade();
    }

    return sum / students.size();
}

int main() {

    int studentsCount = readInt();

    vector<Student> students;

    for (int i = 0; i < studentsCount; ++i) {
        string firstName = readString();
        string lastName = readString();
        float averageGrade = readFloat();

        students.push_back(Student(firstName, lastName, averageGrade));
    }

    for (Student student : students) {
        std::cout << student.getFirstName() + " " + student.getLastName();
        printf(" %.1f\n", student.getAverageGrade());
    }

    std::cout << getAverage(students) << endl;
}