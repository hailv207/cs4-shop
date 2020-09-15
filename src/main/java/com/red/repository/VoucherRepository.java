package com.red.repository;

import com.red.model.Voucher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VoucherRepository extends CrudRepository<Voucher,Long> {
    Optional<Voucher> findByCode(String code);
}
