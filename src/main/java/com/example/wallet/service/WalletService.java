package com.example.wallet.service;

import com.example.wallet.dto.WalletOperationRequest;
import com.example.wallet.entity.Wallet;
import com.example.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void performOperation(WalletOperationRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseGet(() -> {
                    Wallet newWallet = new Wallet();
                    newWallet.setId(request.getWalletId()); // Используем id вместо walletId
                    newWallet.setBalance(BigDecimal.ZERO);
                    return walletRepository.save(newWallet);
                });

        BigDecimal balance = wallet.getBalance();
        BigDecimal amount = request.getAmount();

        switch (request.getOperationType()) {
            case DEPOSIT -> wallet.setBalance(balance.add(amount));
            case WITHDRAW -> {
                if (balance.compareTo(amount) < 0) {
                    throw new IllegalArgumentException("Insufficient funds");
                }
                wallet.setBalance(balance.subtract(amount));
            }
            default -> throw new IllegalArgumentException("Invalid operation type: " + request.getOperationType());
        }

        walletRepository.save(wallet);
    }

    public BigDecimal getBalance(UUID walletId) {
        return walletRepository.findById(walletId)
                .map(Wallet::getBalance)
                .orElse(BigDecimal.ZERO);
    }
}