package com.sbtraining.recipe_project.controllers.views;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class IndexControllerViewTest {

//    // Under Test
//    IndexController indexController;
//
//    @Mock
//    RecipeServiceImpl recipeService;
//
//    @Mock
//    Model model;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        indexController = new IndexController(recipeService);
//    }

//    @Test
//    void testMockMVC() throws Exception {
//
//        // similar to mock server but unit testing without initializing spring context
//        // another method MockMvcBuilders.webAppContextSetup() to initialize with spring context.
//
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
//
//        // attention with the static matchers imports
//        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
//    }

//    @Test
//    void getIndexPage() {
//        // given
//        Set<Recipe> recipes = new HashSet<>();
//        recipes.add(Recipe.builder().id(1L).build());
//        recipes.add(Recipe.builder().id(2L).build());
//
//        when(recipeService.getAllRecipes()).thenReturn(recipes);
//        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
//
//        // when
////        String viewName = indexController.getIndexPage(model);
//
//        // then
//        assertEquals("index", viewName);
//
//        // interactions tests
//        verify(recipeService, times(1)).getAllRecipes();
//        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
//        Set<Recipe> setInController = argumentCaptor.getValue();
//        log.warn("Check that 2 recipes are at test" + Arrays.toString(setInController.toArray()));
//        assertEquals(2, setInController.size());
//    }
}