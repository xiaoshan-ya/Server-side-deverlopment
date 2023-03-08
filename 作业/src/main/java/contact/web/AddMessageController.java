package contact.web;

import contact.Contact;
import contact.Message;
import contact.data.ContactRepository;
import contact.data.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/")
@SessionAttributes("Contact")
public class AddMessageController {

	private MessageRepository messageRepository; //创建一个对象用于对数据库进行操作，同时需要构造函数进行初始化
	private ContactRepository contactRepository;

	@Autowired
	public AddMessageController(MessageRepository messageRepository, ContactRepository contactRepository) {
		this.messageRepository = messageRepository;
		this.contactRepository = contactRepository;
	}

	@Autowired
	private Contact contact;

	@ModelAttribute(name = "contact")
	public Contact contact() {
		return contact;
	}

	@ModelAttribute(name = "message")
	public Message message() {
		return new Message();
	}

	@GetMapping
	public String show(){
		return "addMessage";
	}

	@PostMapping
	public String processMessage(
			@Valid Message message, Errors errors,
			@ModelAttribute Contact contact
	) {
		if (errors.hasErrors()) {
			return "addMessage";
		}

		messageRepository.save(message); //是将message中的信息存入数据库中

		contact.addMessage(message);
		log.info("Processing taco: {}", message);

		contactRepository.save(contact);

		return "addMessage";
	}
}
