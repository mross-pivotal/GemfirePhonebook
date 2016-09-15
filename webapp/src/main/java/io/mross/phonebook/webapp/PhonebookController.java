package io.mross.phonebook.webapp;

import io.mross.phonebook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by mross on 9/13/16.
 */

@Controller
public class PhonebookController {
  @Autowired
  UserRepository usersRepository;

  @RequestMapping("/app/users")
  public String hello(Model model) {
    model.addAttribute("users", usersRepository.findAll());
    return "index";
  }

  @RequestMapping("/")
  @ResponseBody
  public String getUsers() {


    usersRepository.findAll();
    return  usersRepository.findAll().toString();

  }

  @RequestMapping(path = "/api/{id}", method = RequestMethod.GET)
  @ResponseBody
  public User getUser(@PathVariable String id, Model model) {
    System.out.println("asd");
    System.out.println(usersRepository.findOne(id));
    return usersRepository.findOne(id);
  }

  @RequestMapping(path = "/app/users/{id}", method = RequestMethod.GET)
  public String displayUser(@PathVariable String id, Model model) {

    User u = usersRepository.findOne(id);
    model.addAttribute("user",u);

    return "user";
  }



  @RequestMapping("/seed")
  @ResponseBody
  public String seed() {

    UUID id = UUID.randomUUID();
    System.out.println(id);
    User user = new User(id.toString(),"Bobby Jenkins","5169415955","NYC");

    usersRepository.save(user);
    return  usersRepository.findAll().toString();

  }

}
