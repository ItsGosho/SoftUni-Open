cmake_minimum_required(VERSION 3.17)
project(_01_Echo)

set(CMAKE_CXX_STANDARD 11)

add_executable(_01_Echo main.cpp Echo.h Echo.cpp ContactManagerUI.h ContactManagerUI.cpp EmailContact.h EmailContact.cpp)