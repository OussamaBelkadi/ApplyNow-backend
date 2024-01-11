package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.paiement.AccountDTO;
import example.brief.MyRh.dtos.paiement.RequestPaiement;
import example.brief.MyRh.entities.Account;
import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.AccountMapper;
import example.brief.MyRh.repositories.AccountRepository;
import example.brief.MyRh.repositories.CondidateRepository;
import example.brief.MyRh.services.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private CondidateRepository condidateRepository;
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(CondidateRepository condidateRepository, AccountRepository accountRepository) {
        this.condidateRepository = condidateRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = AccountMapper.INSTANCE;
    }

    @Override
    public boolean effectedPayment(RequestPaiement requestPaiement) {
        Account account = this.accountMapper.toEntity(requestPaiement.getAccountDTO());
        long candidateId = requestPaiement.getCandidateId();
        Candidate candidate = this.condidateRepository.findById(candidateId).orElseThrow(()-> new NotExist("the candidate don't exist"));
        account.setCandidate(candidate);
        this.accountRepository.save(account);
        return true;
    }
}
