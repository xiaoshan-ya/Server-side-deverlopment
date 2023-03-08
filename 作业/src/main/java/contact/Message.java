package contact;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity //创建一个数据库表实体，连接到数据库表
public class Message { //保存的信息是一个二维数组，message是内维

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "first name is required")
	private String firstName;

	@NotBlank(message = "last name is required")
	private String lastName;

	@NotBlank(message = "手机号码不能为空")
	@NotNull(message = "手机号不能为空")
	@Length(min = 11, max = 11, message = "手机号只能为11位")
	@Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
	private String phoneNumber;

	@NotBlank(message = "email is required")
	@Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "input correct email")
	private String email;


}
