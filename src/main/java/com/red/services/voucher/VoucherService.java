package com.red.services.voucher;

import com.red.model.Voucher;
import com.red.services.IGeneralService;
import org.springframework.stereotype.Service;

public interface VoucherService extends IGeneralService<Voucher> {
    void setCode(Voucher voucher);
}
