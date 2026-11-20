package pd14.repository;

import pd14.entity.Loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class LoanRepository {
    private final Map<Long, Loan> loans = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            loan.assignId(idSequence.incrementAndGet());
        }
        loans.put(loan.getId(), loan);
        return loan;
    }

    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(loans.get(id));
    }

    public List<Loan> findAll() {
        return List.copyOf(loans.values());
    }
}
