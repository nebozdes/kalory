package com.matveev.kalory.query;

import com.matveev.kalory.WeightCheckTestData;
import com.matveev.kalory.domain.repository.WeightCheckRepository;
import com.matveev.kalory.error.EntityNotFoundException;
import com.matveev.kalory.model.request.list.ListWeightChecks;
import com.matveev.kalory.query.impl.WeightCheckQueriesBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;
import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeightCheckQueriesTest implements WeightCheckTestData {

    private final WeightCheckRepository repository = mock(WeightCheckRepository.class);
    private final WeightCheckQueries queries = new WeightCheckQueriesBean(repository);

    @Test
    public void should_return_check_if_found_by_id() {
        // given
        final var id = 123L;
        final var now = now();
        final var entity = aWeightCheckEntity().id(id).userId(4L).checkTime(now).build();
        final var expectedResult = aWeightCheck().id(weightCheckId(id))
                .userId(userId(4L))
                .checkTime(now)
                .build();
        when(repository.getById(id)).thenReturn(entity);

        // when
        final var result = queries.getById(weightCheckId(id));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_exception_if_not_found_by_id() {
        // given
        final var id = 123L;
        when(repository.getById(id)).thenThrow(new EntityNotFoundException(id));

        // when/then
        assertThatThrownBy(() -> queries.getById(weightCheckId(id)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with ID=123 not found");
    }

    @Test
    public void should_return_empty_page_if_no_check_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        when(repository.findAll(pageObject)).thenReturn(Page.empty());

        // when
        final var result = queries.list(new ListWeightChecks(1, 10));

        // then
        assertThat(result).isEqualTo(Page.empty());
    }


    @Test
    public void should_return_mapped_dtos_if_any_checks_found_for_list() {
        // given;
        final var now = now();
        final var pageObject = PageRequest.of(1, 10);
        final var input = new PageImpl<>(
                List.of(
                        aWeightCheckEntity().id(1L).userId(4L).checkTime(now).build(),
                        aWeightCheckEntity().id(2L).userId(4L).checkTime(now).build(),
                        aWeightCheckEntity().id(3L).userId(4L).checkTime(now).build()
                ),
                pageObject,
                3
        );
        final var expectedResult = new PageImpl<>(
                List.of(
                        aWeightCheck().id(weightCheckId(1L)).userId(userId(4L)).checkTime(now).build(),
                        aWeightCheck().id(weightCheckId(2L)).userId(userId(4L)).checkTime(now).build(),
                        aWeightCheck().id(weightCheckId(3L)).userId(userId(4L)).checkTime(now).build()
                ),
                pageObject,
                3
        );

        when(repository.findAll(pageObject)).thenReturn(input);

        // when
        final var result = queries.list(new ListWeightChecks(1, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(expectedResult.getTotalElements());
        assertThat(result.getPageable()).isEqualTo(expectedResult.getPageable());
        assertThat(result.getContent().size()).isEqualTo(expectedResult.getContent().size());

        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i)).isEqualTo(expectedResult.getContent().get(i));
        }
    }
}
