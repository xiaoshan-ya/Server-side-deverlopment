package tacos;

import lombok.Data;

@Data //省去了get、set构造方法
public class Ingredient {
  
  private final String id;
  private final String name;
  private final Type type;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
