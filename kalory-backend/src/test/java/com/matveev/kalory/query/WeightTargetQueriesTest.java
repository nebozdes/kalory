package com.matveev.kalory.query;

import com.matveev.kalory.WeightTargetTestData;
import com.matveev.kalory.domain.repository.WeightTargetRepository;
import com.matveev.kalory.error.EntityNotFoundException;
import com.matveev.kalory.model.request.list.ListWeightTargets;
import com.matveev.kalory.query.impl.WeightTargetQueriesBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeightTargetQueriesTest implements WeightTargetTestData {

    private final WeightTargetRepository repository = mock(WeightTargetRepository.class);
    private final WeightTargetQueries queries = new WeightTargetQueriesBean(repository);

    @Test
    public void should_return_target_if_found_by_id() {
        // given
        final var id = 123L;
        final var entity = aWeightTargetEntity().id(id).userId(4L).build();
        final var expectedResult = aWeightTarget().id(weightTargetId(id))
                .userId(userId(4L)).build();
        when(repository.getById(id)).thenReturn(entity);

        // when
        final var result = queries.getById(weightTargetId(id));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_exception_if_not_found_by_id() {
        // given
        final var id = 123L;
        when(repository.getById(id)).thenThrow(new EntityNotFoundException(id));

        // when/then
        assertThatThrownBy(() -> queries.getById(weightTargetId(id)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with ID=123 not found");
    }

    @Test
    public void should_return_empty_page_if_no_target_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        when(repository.findAll(pageObject)).thenReturn(Page.empty());

        // when
        final var result = queries.list(new ListWeightTargets(1, 10));

        // then
        assertThat(result).isEqualTo(Page.empty());
    }


    @Test
    public void should_return_mapped_dtos_if_any_targets_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        final var input = new PageImpl<>(
                List.of(
                        aWeightTargetEntity().id(1L).userId(4L).build(),
                        aWeightTargetEntity().id(2L).userId(4L).build(),
                        aWeightTargetEntity().id(3L).userId(4L).build()
                ),
                pageObject,
                3
        );
        final var expectedResult = new PageImpl<>(
                List.of(
                        aWeightTarget().id(weightTargetId(1L)).userId(userId(4L)).build(),
                        aWeightTarget().id(weightTargetId(2L)).userId(userId(4L)).build(),
                        aWeightTarget().id(weightTargetId(3L)).userId(userId(4L)).build()
                ),
                pageObject,
                3
        );

        when(repository.findAll(pageObject)).thenReturn(input);

        // when
        final var result = queries.list(new ListWeightTargets(1, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(expectedResult.getTotalElements());
        assertThat(result.getPageable()).isEqualTo(expectedResult.getPageable());
        assertThat(result.getContent().size()).isEqualTo(expectedResult.getContent().size());

        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i)).isEqualTo(expectedResult.getContent().get(i));
        }
    }
}
