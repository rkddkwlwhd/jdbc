package bcsdbeginner.jdbc.repository;

import bcsdbeginner.jdbc.Domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


class CategoryRepositoryTest {
    CategoryRepository categoryRepository = new CategoryRepository();
    @Test
    void createCategory() throws SQLException {

        Category category = new Category(1, "Category1");
        Category newCategory = categoryRepository.createCategory(category);

        Assertions.assertThat(newCategory.getId()).isEqualTo(1);
    }
}