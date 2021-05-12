#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

class CoordinatePoint {

private:
    int x;
    int y;

public:

    CoordinatePoint() {

    }

    CoordinatePoint(int x, int y) : CoordinatePoint() {
        this->x = x;
        this->y = y;
    }

    int getX() const {
        return this->x;
    }

    void setX(int x) {
        this->x = x;
    }

    int getY() const {
        return this->y;
    }

    void setY(int y) {
        this->y = y;
    }
};

int calculateEuclideanDistance(CoordinatePoint point1, CoordinatePoint point2) {
    int a = point1.getX() - point2.getX();
    int b = point1.getY() - point2.getY();
    int c = pow(a, 2) + pow(b, 2);
    return sqrt(c);
}

int main() {

    int x1;
    int y1;
    int x2;
    int y2;

    cin >> x1;
    cin >> y1;
    cin >> x2;
    cin >> y2;

    CoordinatePoint firstPoint(x1, y1);
    CoordinatePoint secondPoint(x2, y2);

    cout << calculateEuclideanDistance(firstPoint, secondPoint);
}