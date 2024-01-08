package unibuc.RecipeManagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.service.abstractions.TagService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TagControllerTests {
    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;


    @Test
    public void addTag()
    {
        TagDto tagDtoTest = new TagDto(1, "French");

        when(tagService.save(any(TagDto.class))).thenReturn(tagDtoTest);

        var response = tagController.Add(tagDtoTest);

        verify(tagService).save(tagDtoTest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(tagDtoTest);
    }
}
