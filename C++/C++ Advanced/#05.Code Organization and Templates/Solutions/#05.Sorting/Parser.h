#ifndef _03_PARSER_PARSER_H
#define _03_PARSER_PARSER_H

#include <string>
#include <vector>

template<typename T>
class Parser {

private:
    std::istream& in;
    std::string stopLine;

public:
    Parser(std::istream& in, const std::string& stopLine) : in(in), stopLine(stopLine){}

    bool readNext(T& element);
    std::string peek(std::istream in);
};

#include "Parser.tpp"
#endif //_03_PARSER_PARSER_H
