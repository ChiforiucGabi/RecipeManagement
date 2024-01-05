package unibuc.RecipeManagement.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibuc.RecipeManagement.constants.Constants;
import unibuc.RecipeManagement.dto.TagDto;
import unibuc.RecipeManagement.exception.DataAlreadyInDatabaseException;
import unibuc.RecipeManagement.mapper.TagMapper;
import unibuc.RecipeManagement.repository.TagRepository;
import unibuc.RecipeManagement.service.abstractions.TagService;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public TagDto save(TagDto entity) {

        var tag = tagRepository.getTagByName(entity.getName());

        if(tag != null)
        {
            throw new DataAlreadyInDatabaseException(Constants.TAG_ALREADY_EXISTS);
        }

        return TagMapper.convertToDto(tagRepository.save(TagMapper.convertToTag(entity)));
    }
}
