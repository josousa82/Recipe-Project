package com.sbtraining.recipe_project.utils

import com.sbtraining.recipe_project.model.Ingredient

class TestIngredientUtils {

    static final ID_VALUE = 1L
    static final DESCRIPTION = "flower"
    static final AMOUNT = new BigDecimal(1)

    static final ID_VALUE2 = 2L
    static final DESCRIPTION2 = "cinnamon"
    static final AMOUNT2 = new BigDecimal(3)

    static final ingredient_1 = new Ingredient(
            id: ID_VALUE,
            description: DESCRIPTION,
            amount: AMOUNT,
            uom: TestUnitOfMeasureUtils.uom_1
    )

    static final ingredient_2 = new Ingredient(
            id: ID_VALUE2,
            description: DESCRIPTION2,
            amount: AMOUNT2,
            uom: TestUnitOfMeasureUtils.uom_2
    )

    static final listIngredients = [ingredient_1, ingredient_2]
}
