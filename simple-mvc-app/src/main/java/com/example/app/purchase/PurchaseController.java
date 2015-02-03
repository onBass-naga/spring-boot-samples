package com.example.app.purchase;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by naga on 2015/02/01.
 */
@Controller
@RequestMapping("purchase")
public class PurchaseController {

    @ModelAttribute
    PurchaseForm setUpForm() {
        return new PurchaseForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/purchase/create";
    }

    @RequestMapping(value="create", method = RequestMethod.GET)
    public String create(Model model) {
        List<Production> productions = initProductions(); // サンプルなんで手抜き
        model.addAttribute("productions", productions);

        model.addAttribute("paymentMethods", Arrays.asList(PaymentMethod.values()));
        model.addAttribute("prefectures", Arrays.asList(Prefecture.values()));
        return "purchase/create";
    }

    private List<Production> initProductions() {

        List<Production> productions = new ArrayList<>();

        productions.add(new Production(
                1L, "Gradle徹底入門 次世代ビルドツールによる自動化基盤の構築", 4104));
        productions.add(new Production(
                2L, "はじめてのSpring Boot", 2700));
        productions.add(new Production(
                3L, "エリック・エヴァンスのドメイン駆動設計", 5616));

        return productions;
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

        // 永続化処理も省略

        return "purchase/complete";
    }
}
