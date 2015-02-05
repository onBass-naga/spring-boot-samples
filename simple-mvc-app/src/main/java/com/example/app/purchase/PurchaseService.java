package com.example.app.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naga on 2015/02/04.
 */
@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Production> findProductions() {
        List<Production> productions = new ArrayList<>();

        productions.add(new Production(
                1L, "Gradle徹底入門 次世代ビルドツールによる自動化基盤の構築", 4104));
        productions.add(new Production(
                2L, "はじめてのSpring Boot", 2700));
        productions.add(new Production(
                3L, "エリック・エヴァンスのドメイン駆動設計", 5616));

        return productions;
    }

    public void save(PurchaseForm form) {

        Purchase entity = new Purchase();
        entity.setAddress(form.getAddress());
        entity.setEmail(form.getEmail());
        entity.setGiftWrapping(form.getGiftWrapping());
        entity.setName(form.getName());
        entity.setPaymentMethod(form.getPaymentMethod());
        entity.setPrefecture(form.getPrefecture());
        entity.setTel(form.getTel());

        purchaseRepository.save(entity);
    }

    public Purchase findById(Long id) {
        return purchaseRepository.findOne(id);
    }
}
