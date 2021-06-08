package com.pavlyk.restaurant.service;

import com.pavlyk.restaurant.entity.Category;
import com.pavlyk.restaurant.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void getAllCategoryTest(){
        List<Category> expected = List.of(new Category(1L,"category1",new ArrayList<>()),
                new Category(2L,"category2",new ArrayList<>()));
        when(categoryRepository.findAll()).thenReturn(expected);
        List<Category> actual = categoryService.getAllCategory();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getCategoryByNameTest(){
        Category expected = new Category(1L,"category1",new ArrayList<>());
        when(categoryRepository.getCategoryByName("category1")).thenReturn(expected);
        Category actual = categoryService.getCategoryByName("category1");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void saveCategoryTest(){
        Category category = new Category(1L,"category1",new ArrayList<>());
        categoryService.save(category);
        verify(categoryRepository,times(1)).save(eq(category));
    }
}
