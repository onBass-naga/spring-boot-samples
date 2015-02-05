package com.example.app.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by naga on 2015/02/01.
 */
@Controller
@RequestMapping("purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @ModelAttribute
    PurchaseForm setUpForm() {
        return new PurchaseForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/purchase/create";
    }

    @RequestMapping(value="{key}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "key") Long id, Model model) {

        Purchase purchase = purchaseService.findById(id);
        model.addAttribute("purchase", purchase);

        return "purchase/show";
    }

    @RequestMapping(value="create", method = RequestMethod.GET)
    public String create(Model model) {
        List<Production> productions = purchaseService.findProductions(); // サンプルなんで手抜き
        model.addAttribute("productions", productions);

        model.addAttribute("giftWrappings", Arrays.asList(GiftWrapping.values()));
        model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
        model.addAttribute("prefectures", Arrays.asList(Prefecture.values()));
        return "purchase/create";
    }



    @RequestMapping(value="confirm", method = RequestMethod.POST)
    public String confirm(@Validated PurchaseForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return create(model);
        }

        // 実際は在庫チェックとかいろいろやることあるけど省略

        Integer totalPrice = form.getItems().stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("purchaseForm", form);

        return "purchase/confirm";
    }

    @RequestMapping(value="complete", method = RequestMethod.POST)
    public String save(@Validated PurchaseForm form, BindingResult result, Model model) {

        // ここでも実際はチェックとかもろもろやることあるけど省略

        purchaseService.save(form);

        return "purchase/complete";
    }
}
