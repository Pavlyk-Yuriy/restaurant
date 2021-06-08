package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Ingredient;
import com.pavlyk.restaurant.repository.IngredientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    public void getAllIngredientsTest(){
        List<Ingredient> expected = List.of(new Ingredient(1L,"name1"),
                new Ingredient(2L,"name2"));
        when(ingredientRepository.findAll()).thenReturn(expected);
        List<Ingredient> actualList = ingredientService.getAllIngredients();
        Assert.assertEquals(expected,actualList);
    }

    @Test
    public void saveIngredientTest(){
        Ingredient ingredient = new Ingredient(1L,"name1");
        ingredientService.save(ingredient);
        verify(ingredientRepository,times(1)).save(eq(ingredient));
    }
}
