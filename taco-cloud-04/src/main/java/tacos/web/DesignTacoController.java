package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;

import javax.validation.Valid;

import org.springframework.validation.Errors;


@Slf4j //日志记录的插件接口
@Controller //控制器
@RequestMapping("/design") //处理以/design为前缀的URL请求
@SessionAttributes("tacoOrder") //在会话中tacoOrder会一直被服务端维护，不会丢失
public class DesignTacoController {

	@ModelAttribute //向model中添加数据，由Spring调用下面的方法，模型对象是model
	public void addIngredientsToModel(Model model) {

		List<Ingredient> ingredients = Arrays.asList( //配料表
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();//创建一个订单对象
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {//创建一个食品
		return new Taco();
	}

	//在到达这里之前，前面的对象已经处理过了
	@GetMapping //浏览器中的GET请求到这里
	public String showDesignForm() {
		return "design";//返回视图名，在resources/templates中找名为design的html文件
	}

/*
  @PostMapping
  public String processTaco(Taco taco,
  			@ModelAttribute TacoOrder tacoOrder) {
    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);

    return "redirect:/orders/current";
  }
 */

	@PostMapping //如果请求时POST，则代码到这里执行
	public String processTaco(
			@Valid Taco taco, Errors errors /*Valid注解告诉spring需要做校验*/,
			@ModelAttribute TacoOrder tacoOrder /*将spring创建的tacoOrder对象传进来，在55行代码处*/) {

		if (errors.hasErrors()) {
			return "design";//如果有错误转到design.html82行
		}

		//处理流程
		tacoOrder.addTaco(taco);//保存tacoOrder数据，所以将所有的taco保存到tacoOrder中
		log.info("Processing taco: {}", taco);

		return "redirect:/orders/current";//重定向redirect到新的URL上，状态码=302，去order控制器的/current开头的方法
	}

	private Iterable<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
