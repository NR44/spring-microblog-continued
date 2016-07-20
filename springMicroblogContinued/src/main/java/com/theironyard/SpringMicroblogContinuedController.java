package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nigel on 7/19/16.
 */
@Controller
public class SpringMicroblogContinuedController {
    public static final String SESSION_USERNAME = "userName";

    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        String userName = (String) session.getAttribute(SESSION_USERNAME);
        List<Message> messageList = (List) messages.findAll();
        model.addAttribute("userName", userName);
        model.addAttribute("messages", messageList);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute(SESSION_USERNAME, userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path="/add-message", method = RequestMethod.POST)
    public String addMessage(String text){
        Message submittedMessage = new Message(text);
        messages.save(submittedMessage);
        return "redirect:/";
    }

    @RequestMapping(path ="/delete-message", method = RequestMethod.POST)
    /*kept previous, code. No need to iterate?*/
    public String deleteMessage(HttpServletRequest request){
        messages.delete(Integer.valueOf(request.getParameter("id")));
//        String idString = request.getParameter("id");
//        int id = Integer.valueOf(idString);
//        int deleteId = 0;
//
//        Iterator<Message> iterator = messages.iterator();
//        while(iterator.hasNext()){
//            Message message = iterator.next();
//            if(message.getId() == id){
//                iterator.remove();
//            }
//        }
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-message", method = RequestMethod.GET)
    public String editMessage(HttpServletRequest request, Model model){
        String idString = request.getParameter("id");
        int id = Integer.valueOf(idString);

        Message messageToEdit = messages.findById(id);
        model.addAttribute("messageToEdit", messageToEdit);

        return"edit";
    }

    @RequestMapping(path = "/edited", method = RequestMethod.POST)
    public String edited(HttpServletRequest request, String newMessage, int id){
        Message editedMessage = new Message(newMessage);
        messages.delete(id);
        messages.save(editedMessage);

        return "redirect:/";
    }
}