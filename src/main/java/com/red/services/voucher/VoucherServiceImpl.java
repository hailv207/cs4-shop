package com.red.services.voucher;

import com.red.model.Voucher;
import com.red.repository.VoucherRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private Hashids hashids;

    @Override
    public void setCode(Voucher voucher) {
        voucher.setCode(hashids.encode(voucher.getId()));
    }

    @Override
    public Iterable<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public void save(Voucher entity) {
        voucherRepository.save(entity);
    }

    @Override
    public void update(Voucher entity) {
        voucherRepository.save(entity);
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public void delete(Voucher entity) {
        voucherRepository.delete(entity);
    }

}
