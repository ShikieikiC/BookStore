package org.shiki.controller;

import com.nimbusds.jose.JOSEException;
import org.shiki.entity.Address;
import org.shiki.entity.ShoppingCart;
import org.shiki.entity.User;
import org.shiki.entity.dto.UserDTO;
import org.shiki.service.AddressService;
import org.shiki.service.ShoppingCartService;
import org.shiki.service.UserService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    ShoppingCartService shoppingCartService;
    @Resource
    AddressService addressService;


    @RequestMapping("/login")
    public ResData login(@RequestBody User user) throws JOSEException {
        Map<String, Object> loginInfo = userService.login(user.getUsername(), user.getPassword());
        String token = loginInfo.get("token").toString();
        UserDTO userDTO = (UserDTO) loginInfo.get("user");
        return ResData.successLogin(token, userDTO);
    }

    @RequestMapping("/userCart")
    public ResData userCart(HttpServletRequest request) throws ParseException {
        String token = request.getHeader("token");
        List<ShoppingCart> shoppingCarts = shoppingCartService.queryAll(token);
        List<Address> addresses = addressService.queryAll(token);
        return ResData.success(Map.of(
                "shoppingCarts", shoppingCarts,
                "addresses", addresses
        ));

    }
}
