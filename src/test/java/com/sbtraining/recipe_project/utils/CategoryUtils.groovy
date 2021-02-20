package com.sbtraining.recipe_project.utils

import com.sbtraining.recipe_project.model.Category

class CategoryUtils {

    static category1 = new Category(
            id: 1,
            description: "category1",
    )

    static category2 = new Category(
            id: 2,
            description: "category2",
    )

    static category3 = new Category(
            id: 3,
            description: "category3",
    )

    static category4 = new Category(
            id: 4,
            description: "category4",
    )



    static final setOfCategories = [category1, category2, category3, category4] as HashSet

    static Iterable<Category> iterableCategories () {
        [category1, category2, category3, category4]   }


}
