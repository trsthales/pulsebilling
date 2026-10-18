package com.pulsebilling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PulseBillingApplicationTests {

    @Test
    @DisplayName("Should successfully load Spring Boot application context in test profile")
    void contextLoads() {
        // Sanity test to verify Spring container initialization
        assertThat(true).isTrue();
    }
}
