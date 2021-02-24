#include <iostream>
#include <sstream>
#include <string>
#include <stack>
#include <vector>
#include <algorithm>
#include <map>
#include <unordered_map>

using namespace std;

enum class TimeUnit {
    HOUR, MINUTE, SECOND
};

class Time {

private:
    int hours;
    int minutes;
    int seconds;

public:

    Time() {

    }

    Time(int hours, int minutes, int seconds) : Time() {
        this->hours = hours;
        this->minutes = minutes;
        this->seconds = seconds;
    }

    int getTimeAsHours() {
        return this->hours + this->getMinutesAs(TimeUnit::HOUR) + this->getSecondsAs(TimeUnit::HOUR);
    }

    int getTimeAsMinutes() {
        return this->getHoursAs(TimeUnit::MINUTE) + this->minutes + this->getSecondsAs(TimeUnit::MINUTE);
    }

    int getTimeAsSeconds() {
        return this->getHoursAs(TimeUnit::SECOND) + this->getMinutesAs(TimeUnit::SECOND) + this->seconds;
    }

    int getHoursAs(TimeUnit timeUnit) {

        switch (timeUnit) {
            case TimeUnit::HOUR:
                return this->hours;
            case TimeUnit::MINUTE:
                return this->hours * pow(60, 1);
            case TimeUnit::SECOND:
                return this->hours * pow(60, 2);
        }
    }

    int getMinutesAs(TimeUnit timeUnit) {

        switch (timeUnit) {
            case TimeUnit::HOUR:
                return this->minutes / pow(60, 1);
            case TimeUnit::MINUTE:
                return this->minutes;
            case TimeUnit::SECOND:
                return this->minutes * pow(60, 1);
        }
    }

    int getSecondsAs(TimeUnit timeUnit) {

        switch (timeUnit) {
            case TimeUnit::HOUR:
                return this->seconds / pow(60, 2);
            case TimeUnit::MINUTE:
                return this->seconds / pow(60, 1);
            case TimeUnit::SECOND:
                return this->seconds;
        }
    }
};

int main() {

    int hours;
    int minutes;
    int seconds;

    cin >> hours;
    cin >> minutes;
    cin >> seconds;

    Time time(hours, minutes, seconds);

    cout << time.getTimeAsHours() << endl;
    cout << time.getTimeAsMinutes() << endl;
    cout << time.getTimeAsSeconds() << endl;
}