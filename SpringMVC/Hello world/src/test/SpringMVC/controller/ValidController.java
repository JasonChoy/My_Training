package test.SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import test.SpringMVC.domain.User;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by cjs on 2016/9/20.
 * @Valid 表示按照在实体上标记的注解验证参数
 */
@Controller
@RequestMapping("/valid")
public class ValidController {
    @RequestMapping("/userValid")
    public String userValid(User user) throws ParseException {              //因为jsp中使用了modelAttribute属性，所以必须在request域中有一个"user".
        Calendar calendar = new GregorianCalendar(2020, 11, 25,0,0,0);
        Date date = calendar.getTime();
        user.setBirth(date);
        user.setName("cjs");
        return "userValid";
    }
    @RequestMapping("/add")
    public String add(@Valid User user,BindingResult br){
        if(br.getErrorCount()>0){
            return "userValid";
        }
        return "showUser";
    }
}
