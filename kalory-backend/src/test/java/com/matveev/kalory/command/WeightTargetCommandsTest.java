package com.matveev.kalory.command;

import com.matveev.kalory.WeightTargetTestData;
import com.matveev.kalory.command.impl.WeightTargetCommandsBean;
import com.matveev.kalory.domain.entity.WeightTargetEntity;
import com.matveev.kalory.domain.repository.WeightTargetRepository;
import com.matveev.kalory.model.request.create.CreateWeightTarget;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeightTargetCommandsTest implements WeightTargetTestData {

    private final WeightTargetRepository repository = mock(WeightTargetRepository.class);
    private final WeightTargetCommands commands = new WeightTargetCommandsBean(repository);

    @Test
    public void should_call_repository_method_for_create() {
        // given
        final var id = 123L;
        final var expectedResult = aWeightTarget()
                .id(weightTargetId(id))
                .value(BigDecimal.TEN)
                .userId(userId(1L))
                .deadline(Optional.of(now().plusDays(7)))
                .build();

        when(repository.save(any())).thenAnswer(invocation -> {
            final var firstParam = (WeightTargetEntity) invocation.getArguments()[0];
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
        commands.delete(weightTargetId(id));

        // then
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

    private CreateWeightTarget createRequest() {
        final var request = new CreateWeightTarget();
        request.setDeadline(now().plusDays(7));
        request.setValue(BigDecimal.TEN);
        request.setUserId(userId(1L));
        return request;
    }
}
