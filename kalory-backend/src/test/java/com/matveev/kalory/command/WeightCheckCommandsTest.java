package com.matveev.kalory.command;

import com.matveev.kalory.WeightCheckTestData;
import com.matveev.kalory.command.impl.WeightCheckCommandsBean;
import com.matveev.kalory.domain.entity.WeightCheckEntity;
import com.matveev.kalory.domain.repository.WeightCheckRepository;
import com.matveev.kalory.model.request.create.CreateWeightCheck;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Instant;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeightCheckCommandsTest implements WeightCheckTestData {

    private final WeightCheckRepository repository = mock(WeightCheckRepository.class);
    private final WeightCheckCommandsBean commands = new WeightCheckCommandsBean(repository);

    private final Instant now = Instant.now();

    @Test
    public void should_call_repository_method_for_create() {
        // given
        final var id = 123L;
        final var expectedResult = aWeightCheck()
                .id(weightCheckId(id))
                .checkTime(now)
                .value(BigDecimal.TEN)
                .userId(userId(1L))
                .build();

        when(repository.save(any())).thenAnswer(invocation -> {
            final var firstParam = (WeightCheckEntity) invocation.getArguments()[0];
            firstParam.setId(id);
            return firstParam;
        });

        // when
        final var result = commands.create(createRequest());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_call_repository_method_for_delete() {
        // given
        final var id = 123L;
        doNothing().when(repository).delete(any());

        // when
        commands.delete(weightCheckId(id));

        // then
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

    private CreateWeightCheck createRequest() {
        final var request = new CreateWeightCheck();
        request.setCheckTime(now);
        request.setValue(BigDecimal.TEN);
        request.setUserId(userId(1L));
        return request;
    }
}
