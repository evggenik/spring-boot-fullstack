package com.amigoscode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest // не использовать это, иначе загрузятся все бины, а мы тестим только один слой!!!
public class TestcontainersTest extends AbstractTestcontainers {

    @Test
    void canStartPostgresDb() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }

}
