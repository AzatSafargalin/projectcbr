package ru.cbr.project;

import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author Azat Safargalin
 */
@SpringBootTest
@ActiveProfiles("test")
public class TestClass {

    @Test
    void testMePlease() {
        Assert.isEqual(1, 1, "test message");
    }
}
