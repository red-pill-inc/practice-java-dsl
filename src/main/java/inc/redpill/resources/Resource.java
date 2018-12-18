package inc.redpill.resources;

import inc.redpill.groups.ResourceCheck;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

@AResource(groups = ResourceCheck.class)
@GroupSequence({ResourceCheck.class, Resource.class})
public class Resource {

    @NotBlank
    private String type;

    public static final class ResourceBuilder {
        private String type;

        private ResourceBuilder() {
        }

        public static ResourceBuilder aResource() {
            return new ResourceBuilder();
        }

        public ResourceBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public Resource build() {
            Resource resource = new Resource();
            resource.type = this.type;
            return resource;
        }
    }
}
