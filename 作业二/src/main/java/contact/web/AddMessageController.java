package contact.web;

import contact.Contact;
import contact.Message;
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

		contact.addMessage(message);
		log.info("Processing taco: {}", message);

		return "addMessage";
	}
}
