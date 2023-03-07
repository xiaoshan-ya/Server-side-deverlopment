package tacos.data;

import java.util.Optional;

import tacos.Ingredient;

//业务层到Dao层一定要有接口
public interface IngredientRepository {//控制配料表

  Iterable<Ingredient> findAll();
  
  Optional<Ingredient> findById(String id);//根据ID取
  
  Ingredient save(Ingredient ingredient);
  
}
