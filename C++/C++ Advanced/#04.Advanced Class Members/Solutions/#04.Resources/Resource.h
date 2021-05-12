#include "ResourceType.h"
#include "string"
#include "iostream"
#include "algorithm"

namespace SoftUni {

    class Resource {

    private:
        int id;
        ResourceType type;
        std::string link;

    public:

        int getId() const {
            return id;
        }

        ResourceType getType() const {
            return type;
        }

        const std::string& getLink() const {
            return link;
        }


        friend bool operator>(const Resource& r1, const Resource& r2);

        friend bool operator<(const Resource& r1, const Resource& r2);

        friend bool operator==(const Resource& r1, const Resource& r2);

        friend std::istream& operator>>(std::istream& istream, Resource& resource);

        friend std::ostream& operator<<(std::ostream& ostream, const Resource& resource);
    };

    void toLowerCase(std::string& str) {
        std::transform(str.begin(), str.end(), str.begin(),
                       [](unsigned char c) { return std::tolower(c); });
    }

    ResourceType getResourceTypeByName(std::string name) {

        toLowerCase(name);

        if (name == "demo")
            return ResourceType::DEMO;

        if (name == "presentation")
            return ResourceType::PRESENTATION;

        return ResourceType::VIDEO;
    }

    bool operator>(const Resource& r1, const Resource& r2) {
        return r1.getId() > r2.getId();
    }

    bool operator<(const Resource& r1, const Resource& r2) {
        return r1.getId() < r2.getId();
    }

    bool operator==(const Resource& r1, const Resource& r2) {
        return r1.getId() == r2.getId();
    }

    std::istream& operator>>(std::istream& istream, Resource& resource) {
        istream >> resource.id;

        std::string typeName;
        istream >> typeName;
        resource.type = getResourceTypeByName(typeName);

        istream >> resource.link;
        return istream;
    }

    std::ostream& operator<<(std::ostream& ostream, const Resource& resource) {
        ostream << resource.id << " " << resource.type << " " << resource.link;
        return ostream;
    }
}