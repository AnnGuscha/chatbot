package by.gstu.bot.learning.web.controller;

import by.gstu.bot.learning.domain.User;
import by.gstu.bot.learning.service.UserService;
import by.gstu.bot.learning.util.SessionUtil;
import by.gstu.bot.learning.web.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    UserFormValidator userFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }


    // edit user
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userForm") @Validated User user,
                           BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "jsp/user/userForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "user_updated_successfully");
            userService.edit(user);

            // POST/REDIRECT/GET
            return "redirect:/user";
        }

    }

    // show update form
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUserForm(Model model) {

        User user = userService.get(SessionUtil.getSessionUser().getId());
        model.addAttribute("userForm", user);

        return "jsp/user/userForm";

    }

    // delete user
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public String deleteUser(final RedirectAttributes redirectAttributes) {

        userService.remove(SessionUtil.getSessionUser().getId());

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");

        return "redirect:/index";

    }
}
