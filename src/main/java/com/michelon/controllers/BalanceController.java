package com.michelon.controllers;

import com.michelon.dto.response.BalanceResponseDto;
import com.michelon.dto.response.ErrorDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.model.Account;
import com.michelon.service.AccountService;

import spark.Request;

public class BalanceController implements Controller {

    public static final String PARAM_ACCOUNT_ID = "accountId";
    public static final String ROUTE = "/:accountId/balance";

    private final AccountService accountService;

    public BalanceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseDto<BalanceResponseDto> handle(Request request) {
        try {
        	return getBalance(request.params(PARAM_ACCOUNT_ID));
		} catch (AccountNotFoundException e) {
			return new ResponseDto<>(404, new ErrorDto(e.getMessage()));
		}
    }

	private ResponseDto<BalanceResponseDto> getBalance(String accountId) throws AccountNotFoundException {
		Account account = accountService.getAccount(accountId);
		BalanceResponseDto balanceDto = new BalanceResponseDto(account.getBalance());
		return new ResponseDto<BalanceResponseDto>(200, balanceDto);
	}

	@Override
	public String getRoute() {
		return ROUTE;
	}
    
}