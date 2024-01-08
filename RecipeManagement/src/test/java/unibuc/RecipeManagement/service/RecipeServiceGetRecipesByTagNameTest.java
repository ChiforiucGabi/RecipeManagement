package unibuc.RecipeManagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.RecipeDto;
import unibuc.RecipeManagement.entity.Recipe;
import unibuc.RecipeManagement.entity.Tag;
import unibuc.RecipeManagement.exception.DataNotFoundException;
import unibuc.RecipeManagement.repository.RecipeRepository;
import unibuc.RecipeManagement.repository.TagRepository;
import unibuc.RecipeManagement.service.implementations.RecipeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceGetRecipesByTagNameTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    public void getRecipesByTagName_happyPath()
    {
        Tag tag = new Tag(1, "test", null);
        var recipeList = List.of(new Recipe(1, "test","test", 10, null, null, null));
        var recipeDtoList = List.of(new RecipeDto(1, "test", "test", 10, null));

        when(recipeRepository.getRecipeByTagId(any(Integer.class))).thenReturn(recipeList);
        when(tagRepository.getTagByName(any(String.class))).thenReturn(tag);

        var resultDto = recipeService.getRecipesByTagName(tag.getName());

        assertThat(resultDto).isEqualTo(recipeDtoList);
        verify(recipeRepository).getRecipeByTagId(any(Integer.class));
        verify(tagRepository).getTagByName(any(String.class));

    }

    @Test
    public void getRecipesByTagName_unhappyPath_tagDoesntExist()
    {
        Tag tag = new Tag(1, "test", null);

        when(tagRepository.getTagByName(any(String.class))).thenReturn(null);

        assertThatThrownBy(() -> recipeService.getRecipesByTagName(tag.getName()))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.TAG_NAME_DOES_NOT_EXIST);

        verify(tagRepository).getTagByName(any(String.class));

    }
}
