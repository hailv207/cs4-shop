package com.red.app.http.controller.voucher;

import com.red.model.Voucher;
import com.red.services.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        modelAndView.addObject("voucher", voucher.get());
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteVouchers(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("vouchers/delete");
        Optional<Voucher> voucher = voucherService.findById(id);
        modelAndView.addObject("voucher", voucher.get());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createVoucher(@ModelAttribute("voucher") Voucher voucher, RedirectAttributes redirectAttributes) {
        voucherService.save(voucher);
        voucherService.setCode(voucher);
       redirectAttributes.addFlashAttribute("create_done",true);
        return "redirect:/admin/vouchers";
    }

    @PostMapping("/{id}/edit")
    public String editVoucher(@ModelAttribute("voucher") Voucher voucher, RedirectAttributes redirectAttributes) {
        voucherService.save(voucher);
        redirectAttributes.addFlashAttribute("edit_done",true);
        return "redirect:/admin/vouchers";
    }

    @PostMapping("/{id}/delete")
    public String deleteVoucher(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        voucherService.deleteById(id);
       redirectAttributes.addFlashAttribute("delete_done",true);
        return "redirect:/admin/vouchers";
    }
}
