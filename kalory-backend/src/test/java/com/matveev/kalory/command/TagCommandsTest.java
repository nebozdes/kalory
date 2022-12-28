package com.matveev.kalory.command;

import com.matveev.kalory.TagTestData;
import com.matveev.kalory.command.impl.TagCommandsBean;
import com.matveev.kalory.domain.entity.TagEntity;
import com.matveev.kalory.domain.repository.TagRepository;
import com.matveev.kalory.model.request.create.CreateTag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.matveev.kalory.model.id.TagId.tagId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagCommandsTest implements TagTestData {

    private final TagRepository repository = mock(TagRepository.class);
    private final TagCommandsBean commands = new TagCommandsBean(repository);

    @Test
    public void should_call_repository_method_for_create() {
        // given
        final var id = 123L;
        final var expectedResult = aTag().id(tagId(id)).name("Some name").build();

        when(repository.save(any())).thenAnswer(invocation -> {
            final var firstParam = (TagEntity) invocation.getArguments()[0];
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
        commands.delete(tagId(id));

        // then
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

    private CreateTag createRequest() {
        final var request = new CreateTag();
        request.setName("Some name");
        return request;
    }
}
