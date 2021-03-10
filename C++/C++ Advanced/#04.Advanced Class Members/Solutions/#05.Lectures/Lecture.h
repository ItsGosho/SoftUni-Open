#include <map>
#include <vector>
#include <iterator>

namespace SoftUni {

    class Lecture {

    private:
        std::set<Resource> resources;
        std::map<ResourceType, int> numberOfResourcesByType;

    public:

        std::set<Resource> addResource(Resource resource) {

            auto test = this->resources.erase(resource);
            this->resources.insert(resource);

            if (test == 0)
                this->numberOfResourcesByType[resource.getType()]++;

            return this->resources;
        }

        std::map<ResourceType, int> getNumberOfResourcesByType() const {
            return this->numberOfResourcesByType;
        };

        auto begin() {
            return this->resources.begin();
        }

        auto end() {
            return this->resources.end();
        }

        friend Lecture& operator<<(Lecture& lecture, const Resource& resource);

        friend std::vector<ResourceType>& operator<<(std::vector<ResourceType>& resourceTypes, const Lecture& lecture);

        int operator[](ResourceType resourceType) {
            return this->numberOfResourcesByType[resourceType];
        }
    };

    Lecture& operator<<(Lecture& lecture, const Resource& resource) {
        lecture.addResource(resource);
        return lecture;
    }

    std::vector<ResourceType>& operator<<(std::vector<ResourceType>& resourceTypes, const Lecture& lecture) {

        for (const auto& item : lecture.getNumberOfResourcesByType()) {
            resourceTypes.push_back(item.first);
        }

        return resourceTypes;
    }
}

