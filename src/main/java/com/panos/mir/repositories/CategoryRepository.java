package com.panos.mir.repositories;

import com.panos.mir.model.Categories;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Panos on 18-May-17.
 */
public interface CategoryRepository extends CrudRepository<Categories, Integer> {
}
