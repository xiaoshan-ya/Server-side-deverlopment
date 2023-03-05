package tacos;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data //lombok注释: 自动补充数据，编译时有用，编译完没有作用
public class Taco {

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")//请求spring自动帮我校验; name长度至少为min=5
  private String name;

  @NotNull
  @Size(min=1, message="You must choose at least 1 ingredient")//制作taco
  private List<Ingredient> ingredients;

}
