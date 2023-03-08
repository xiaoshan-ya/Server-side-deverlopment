# 服务端开发

### 5 数据持久化

将所需要保存的类添加@Entity注解，为当前类指定一个数据库实例，同时需要表明@Id注解

```java
@Data
@Entity //创建一个数据库表实体，连接到数据库表
public class Message { //保存的信息是一个二维数组，message是内维

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
```

接着需要一个接口对于Dao层进行操作，需要继承CrudRepository，其中用Message绑定类

```java
public interface MessageRepository
         extends CrudRepository<Message, String> { //将Message和数据库绑定，将Message的值作为属性存储到数据库中
  
}
```

在Controller中需要创建可操作数据库的对象，并且需要创建构造函数

```java
	private MessageRepository messageRepository; //创建一个对象用于对数据库进行操作，同时需要构造函数进行初始化
	private ContactRepository contactRepository;

	@Autowired
	public AddMessageController(MessageRepository messageRepository, ContactRepository contactRepository) {
		this.messageRepository = messageRepository;
		this.contactRepository = contactRepository;
	}
```

在调用时使用save将数据存储进数据库中，只需要将对象传入，对象中的所有变量都会作为属性保存在数据库中

```java
	@PostMapping
	public String processMessage(
			@Valid Message message, Errors errors,
			@ModelAttribute Contact contact
	) {
		if (errors.hasErrors()) {
			return "addMessage";
		}

		messageRepository.save(message); //是将message中的信息存入数据库中
```

