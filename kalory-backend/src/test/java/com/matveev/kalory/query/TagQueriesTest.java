package com.matveev.kalory.query;

import com.matveev.kalory.TagTestData;
import com.matveev.kalory.domain.repository.TagRepository;
import com.matveev.kalory.error.EntityNotFoundException;
import com.matveev.kalory.model.request.list.ListTags;
import com.matveev.kalory.query.impl.TagQueriesBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.matveev.kalory.model.id.TagId.tagId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagQueriesTest implements TagTestData {

    private final TagRepository repository = mock(TagRepository.class);
    private final TagQueries queries = new TagQueriesBean(repository);

    @Test
    public void should_return_tag_if_found_by_id() {
        // given
        final var id = 123L;
        final var entity = aTagEntity().id(id).name("Some tag").build();
        final var expectedResult = aTag().id(tagId(id)).name("Some tag").build();
        when(repository.getById(id)).thenReturn(entity);

        // when
        final var result = queries.getById(tagId(id));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_exception_if_not_found_by_id() {
        // given
        final var id = 123L;
        when(repository.getById(id)).thenThrow(new EntityNotFoundException(id));

        // when/then
        assertThatThrownBy(() -> queries.getById(tagId(id)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with ID=123 not found");
    }

    @Test
    public void should_return_empty_page_if_no_tagfound_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        when(repository.findAll(pageObject)).thenReturn(Page.empty());

        // when
        final var result = queries.list(new ListTags(1, 10));

        // then
        assertThat(result).isEqualTo(Page.empty());
    }


    @Test
    public void should_return_mapped_dtos_if_any_tags_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        final var input = new PageImpl<>(
                List.of(
                        aTagEntity().id(1L).name("Tag 1").build(),
                        aTagEntity().id(2L).name("Tag 2").build(),
                        aTagEntity().id(3L).name("Tag 3").build()
                ),
                pageObject,
                3
        );
        final var expectedResult = new PageImpl<>(
                List.of(
                        aTag().id(tagId(1L)).name("Tag 1").build(),
                        aTag().id(tagId(2L)).name("Tag 2").build(),
                        aTag().id(tagId(3L)).name("Tag 3").build()
                ),
                pageObject,
                3
        );

        when(repository.findAll(pageObject)).thenReturn(input);

        // when
        final var result = queries.list(new ListTags(1, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(expectedResult.getTotalElements());
        assertThat(result.getPageable()).isEqualTo(expectedResult.getPageable());
        assertThat(result.getContent().size()).isEqualTo(expectedResult.getContent().size());

        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i)).isEqualTo(expectedResult.getContent().get(i));
        }
    }
}
