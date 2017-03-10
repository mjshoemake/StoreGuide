package mjs.home.controllers;

import mjs.common.core.BaseController;
import mjs.home.services.FranchiseService;
import mjs.model.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FranchiseController extends BaseController {

    @Autowired
    FranchiseService service;

    public FranchiseController() {
        super("mjs.model.Franchise", "franchises", "name", "franchise_pk", "Franchise");
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.GET)
    @ResponseBody public ResponseEntity getList(Model model) {
        return super.getList(model, service);
    }

    @RequestMapping(value = "/franchises/{pk}", method = RequestMethod.GET)
    @ResponseBody public ResponseEntity getByPK(Model model, @PathVariable int pk) {
        return super.getByPK(model, pk, service);
    }

    @RequestMapping(value = "/franchises/{pkList}", method = RequestMethod.DELETE)
    @ResponseBody public ResponseEntity delete(Model model, @PathVariable String pkList) {
        return super.delete(model, pkList, service);
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.POST)
    @ResponseBody public ResponseEntity save(Model model, @RequestBody Franchise entity) {
        return super.update(model, entity, service);
    }

    @RequestMapping(value = "/franchises", method = RequestMethod.PUT)
    @ResponseBody public ResponseEntity update(Model model, @RequestBody Franchise entity) {
        return super.update(model, entity, service);
    }
}
