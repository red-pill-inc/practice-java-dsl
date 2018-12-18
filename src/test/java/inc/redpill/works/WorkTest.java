package inc.redpill.works;

import inc.redpill.holes.Hole;
import inc.redpill.holes.Hole.HoleBuilder;
import inc.redpill.practices.Practice;
import inc.redpill.practices.Practice.PracticeBuilder;
import inc.redpill.resources.Resource;
import inc.redpill.resources.Resource.ResourceBuilder;
import inc.redpill.works.Work.WorkBuilder;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class WorkTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        Map<String, Object> context = new HashMap<>();
        context.put("foo", "bar");
        validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .constraintValidatorPayload(context)
                .buildValidatorFactory()
                .getValidator();
    }

    @Test
    void shouldBuildWork() {
        Hole someHole = HoleBuilder.aHole()
                .withType("Foo")
                .build();

        Practice somePractice = PracticeBuilder.aPractice()
                .withName("SomePractice")
                .withResultType("Foo")
                .withResourceTypes(asList("Bar"))
                .build();

        Resource someResource = ResourceBuilder.aResource()
                .withType("Bar")
                .build();

        Work someWork = WorkBuilder.aWork()
                .withHole(someHole)
                .withPractice(somePractice)
                .withResources(emptyList())
                .withWorks(emptyList())
                .build();

        Work anotherWork = WorkBuilder.aWork()
                .withHole(someHole)
                .withPractice(somePractice)
                .withResources(asList(someResource))
                .withWorks(asList(someWork))
                .build();

        assertThat(validator.validate(anotherWork)).isEmpty();
//        assertThat(validator.validate(anotherWork, WorkCheck.class)).isEmpty();
//        assertThat(validator.validate(anotherWork, InferenceCheck.class)).isEmpty();
    }
}
