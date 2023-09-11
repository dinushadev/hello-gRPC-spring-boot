package pl.piomin.services.grpc.grpctestservice2.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.grpc.account.model.AccountProto;
import pl.piomin.services.grpc.grpctestservice2.repository.AccountRepository;

@RestController
public class AccountController {

	@Autowired
	AccountRepository repository;

	protected Logger logger = Logger.getLogger(AccountController.class.getName());

	@RequestMapping(value = "/accounts/{number}", produces = "application/x-protobuf")
	public AccountProto.Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		return repository.findByNumber(number);
	}

	@RequestMapping(value = "/accounts/customer/{customer}", produces = "application/x-protobuf")
	public AccountProto.Accounts findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return AccountProto.Accounts.newBuilder().addAllAccount(repository.findByCustomer(customerId)).build();
	}

	@RequestMapping(value = "/accounts")
	public AccountProto.Accounts findAll() {
		logger.info("Account.findAll()");
		return AccountProto.Accounts.newBuilder().addAllAccount(repository.findAll()).build();
	}

}