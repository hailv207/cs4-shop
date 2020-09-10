package com.red.app.http.controller.voucher;

import com.red.model.Voucher;
import com.red.services.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/vouchers")
public class VoucherManagerController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public ModelAndView showAllVouchers() {
        ModelAndView modelAndView = new ModelAndView("vouchers/home");
        Iterable<Voucher> vouchers = voucherService.findAll();
        modelAndView.addObject("vouchers", vouchers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createVouchers() {
        ModelAndView modelAndView = new ModelAndView("vouchers/create");
        modelAndView.addObject("voucher", new Voucher());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView createVouchers(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("vouchers/edit");
        Optional<Voucher> voucher = voucherService.findById(id);
        modelAndView.addObject("voucher", voucher);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteVouchers(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("vouchers/delete");
        Optional<Voucher> voucher = voucherService.findById(id);
        modelAndView.addObject("voucher", voucher);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createVoucher(@ModelAttribute("voucher") Voucher voucher) {
        voucherService.save(voucher);
        ModelAndView modelAndView = new ModelAndView("vouchers/home");
        modelAndView.addObject("create_done", true);
        modelAndView.addObject("vouchers",voucherService.findAll());
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editVoucher(@ModelAttribute("voucher") Voucher voucher) {
        voucherService.save(voucher);
        ModelAndView modelAndView = new ModelAndView("vouchers/home");
        modelAndView.addObject("edit_done", true);
        modelAndView.addObject("vouchers",voucherService.findAll());
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteVoucher(@PathVariable("id") Long id) {
        voucherService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("vouchers/home");
        modelAndView.addObject("delete_done", true);
        modelAndView.addObject("vouchers",voucherService.findAll());
        return modelAndView;
    }
}
