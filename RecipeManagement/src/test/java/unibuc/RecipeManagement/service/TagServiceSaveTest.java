package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.entity.Tag;
import unibuc.RecipeManagement.exception.DataAlreadyInDatabaseException;
import unibuc.RecipeManagement.mapper.TagMapper;
import unibuc.RecipeManagement.repository.TagRepository;
import unibuc.RecipeManagement.service.implementations.TagServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TagServiceSaveTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void tagSave_happyPath(){
        TagDto tagDtoTest = new TagDto(1, "French");
        var tagToSave = TagMapper.convertToTag(tagDtoTest);
        Tag savedTag = new Tag(1,"French",null);

        when(tagRepository.save(any(Tag.class))).thenReturn(savedTag);

        TagDto resultDto = tagService.save(tagDtoTest);

        verify(tagRepository).save(tagToSave);
        assertThat(resultDto).isEqualTo(tagDtoTest);
    }

    @Test
    public void tagSave_unhappyPath_TagAlreadyInDatabase(){
        TagDto tagDtoTest = new TagDto(1, "French");
        Tag tag = new Tag(1,"French",null);

        when(tagRepository.getTagByName("French")).thenReturn(tag);

        assertThatThrownBy(() -> tagService.save(tagDtoTest))
                .isInstanceOf(DataAlreadyInDatabaseException.class)
                    .hasMessage(Constants.TAG_ALREADY_EXISTS);

        verify(tagRepository).getTagByName("French");
    }
}
