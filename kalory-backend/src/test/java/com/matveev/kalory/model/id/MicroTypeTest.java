package com.matveev.kalory.model.id;


import org.junit.jupiter.api.Test;

import static com.matveev.kalory.model.id.UserId.userId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MicroTypeTest {

    @Test
    public void should_fail_on_null_id_value() {
        assertThatThrownBy(() -> userId(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Value should not be null!");
    }

    @Test
    public void should_create_object_on_non_null_value() {
        final var userId = userId(123L);
        assertThat(userId.value()).isEqualTo(123L);
    }
}
